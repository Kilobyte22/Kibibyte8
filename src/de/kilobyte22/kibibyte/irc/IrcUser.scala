package de.kilobyte22.kibibyte.irc

import de.kilobyte22.kibibyte.chat.{Bot, Chat, CommandSender, User}

class IrcUser(val nick: String, val bot: IrcBot) extends User {

  var username = ""
  var host = None

  override def account: String = nick // TODO: FIX

  /**
   * A serverwise ban, may throw PermissionException
   * @return The user itself, for easy chaining
   */
  override def ban: User = ???

  /**
   * Starts a private chat with this user
   * @return the private chat
   */
  override def chatWith: Chat = bot.getChat(nick).get

  /**
   * A serverwide kick. May throw PermissionException
   * @param message A kick message, optional
   * @return The user itself, for easy chaining
   */
  override def kick(message: String): IrcUser = ???

  override def sendError(message: String): IrcUser = ???

  override def verbose_=(value: Boolean): Boolean = value

  override def verbose: Boolean = false

  override def canVerbose: Boolean = false

  override def sendMessage(message: String): IrcUser = ???

  override def commandNotFound(command: String): CommandSender = sendError(s"Command not found: $command")
}
