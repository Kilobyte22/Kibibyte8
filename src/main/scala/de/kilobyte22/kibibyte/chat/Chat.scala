package de.kilobyte22.kibibyte.chat

trait Chat {

  def bot: Bot

  /**
   * A serverwise unique id for the chat
   * @return
   */
  def id: String

  def name: String

  /**
   * Join this chat
   * @return This Chat, for easier chaining
   */
  def join(): Chat

  /**
   * Leave the chat
   * @return This Chat, for easier chaining
   */
  def leave(): Chat

  /**
   * Gets all users in this chat
   * @return The list of all users
   */
  def users: Iterable[User]

  /**
   * Forcibly removes a user from this chat
   *
   * May Throw [[de.kilobyte22.kibibyte.exception.PermissionException]]
   * @param user The user to remove
   * @param message An optional comment for the removal
   * @return This Chat, for easier chaining
   */
  def kick(user: User, message: String = null): Chat

  /**
   * Takes a users ability to reenter the chat again
   *
   * May throw [[de.kilobyte22.kibibyte.exception.PermissionException]]
   * @param user The target user
   * @return This Chat, for easier chaining
   */
  def ban(user: User): Chat

  /**
   * ban() followed by kick()
   * @param user The user to affect
   * @param message An optional comment
   * @return This Chat, for easier chaining
   */
  def kickban(user: User, message: String = null) = ban(user).kick(user, message)

  /**
   * Sends a simple text message
   * @param message The message to send
   * @return This Chat, for easier chaining
   */
  def send(message: String): Chat

  def senderFor(user: User): CommandSender
}
