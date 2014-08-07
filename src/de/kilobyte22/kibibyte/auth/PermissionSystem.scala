package de.kilobyte22.kibibyte.auth

import de.kilobyte22.kibibyte.chat.{User, Chat}

import scala.collection.mutable

object PermissionSystem {
  private val permissionHandlers = mutable.Map.empty[String, PermissionHandler]
  private var _permissionHandler: PermissionHandler = null
  val specialGroupHandlers = mutable.ArrayBuffer.empty[SpecialGroupHandler]

  def hasPermission(user: User, chat: Chat, permission: String) = {
    val permissions = splitNode(permission)
    userHasPermission(user, chat, permissions) orElse userHasPermission(null, chat, permissions) match {
      case Some(value: Boolean) => value
      case None => false
    }
  }

  private def userHasPermission(user: User, chat: Chat, permissions: Iterable[String]): Option[Boolean] = {

    // first we check the users permissions directly
    permissions.foreach(perm =>
      _permissionHandler.hasPermission(user, perm) match {
        case Some(p) => return Some(p)
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
            }
          )
        })
      )

    // Finally we check regular groups of the user
    _permissionHandler.groupsFor(user).foreach(group =>
      permissions.foreach(perm =>
        group.hasPermission(perm) match {
          case Some(p) => return Some(p)
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
