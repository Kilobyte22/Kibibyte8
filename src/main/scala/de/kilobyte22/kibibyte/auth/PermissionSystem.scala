package de.kilobyte22.kibibyte.auth

import de.kilobyte22.kibibyte.api.auth.{SpecialGroupHandler, PermissionHandler, Identity}
import de.kilobyte22.kibibyte.api.chat.{Chat, User}
import de.kilobyte22.kibibyte.core.Server

import scala.collection.mutable

object PermissionSystem {
  private val permissionHandlers = mutable.Map.empty[String, PermissionHandler]
  private var _permissionHandler: PermissionHandler = null
  val specialGroupHandlers = mutable.ArrayBuffer.empty[SpecialGroupHandler]

  def hasPermission(user: User, chat: Chat, permission: String): Boolean = {
    val permissions = splitNode(permission)
    val accounts = Server.serverFor(chat.bot).loginHandler.getAccountsForUser(user)
    val identities = accounts.map((account) => account.identity) ++ List(_permissionHandler.getOrCreateIdentity("default"))

    identities.foreach((id) => {
      userHasPermission(user, id, chat, permissions) match {
        case Some(v: Boolean) => return v
        case None => Unit
      }
    })
    false
  }

  private def userHasPermission(user: User, identity: Identity, chat: Chat, permissions: Iterable[String]): Option[Boolean] = {

    // first we check the users permissions directly
    permissions.foreach(perm =>
      _permissionHandler.hasPermission(identity, perm) match {
        case Some(p) => return Some(p)
        case None => Unit
      }
    )

    // Then we search special groups (like special.chat.operator)
    if (user != null)
      specialGroupHandlers.foreach(h =>
        h.groupsFor(user, chat).foreach(g => {
          val group = getGroup(g)
          permissions.foreach(perm =>
            group.hasPermission(perm) match {
              case Some(p) => return Some(p)
              case None => Unit
            }
          )
        })
      )

    // Finally we check regular groups of the user
    _permissionHandler.groupsFor(identity).foreach(group =>
      permissions.foreach(perm =>
        group.hasPermission(perm) match {
          case Some(p) => return Some(p)
          case None => Unit
        }
      )
    )

    None
  }

  def getGroup(name: String) = _permissionHandler.getGroup(name)

  def splitNode(string: String) = {
    val parts = string.split("\\.")
    val ret = mutable.ArrayBuffer.empty[String]
    ret += "*"
    var tmp = ""
    parts.foreach(el => {
      if (tmp != "")
        tmp += "."
      tmp += el
      ret += (if (tmp == string)
        tmp
      else
        tmp + ".*")
    })
    ret.reverse
  }
  
  def permissionHandler = _permissionHandler
  def permissionHandler_=(name: String) = {
    if (permissionHandlers.contains(name)) {
      if (_permissionHandler != null)
        _permissionHandler.onUnregister()

      _permissionHandler = permissionHandlers(name)

      _permissionHandler.onRegister()
    }
  }
}