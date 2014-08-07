package de.kilobyte22.kibibyte.chat

import de.kilobyte22.kibibyte.auth.PermissionSystem

trait User extends CommandSender {
  /**
   * @return The nick of the user
   */
  def nick: String

  /**
   * @return The username of this user
   */
  def username: String

  /**
   * @return The hostname or ip of the origin of the users connection
   */
  def host: Option[String]

  /**
   * @return The hostmask of the user
   */
  def hostmask: String

  /**
   * A serverwide kick. May throw PermissionException
   * @param message A kick message, optional
   * @return The user itself, for easy chaining
   */
  def kick(message: String = null): User

  /**
   * A serverwise ban, may throw PermissionException
   * @return The user itself, for easy chaining
   */
  def ban: User

  /**
   * A serverwide ban, followed by a server wide kick
   * @param message
   * @return The user itself, for easy chaining
   */
  def kickban(message: String = null) = ban.kick(message)

  /**
   * Starts a private chat with this user
   * @return the private chat
   */
  def chatWith: Chat

  /**
   *
   * @return
   */
  def bot: Bot

  def account: String

  def hasPermission(name: String, chat: Chat = null) = PermissionSystem.hasPermission(this, chat, name)
}
