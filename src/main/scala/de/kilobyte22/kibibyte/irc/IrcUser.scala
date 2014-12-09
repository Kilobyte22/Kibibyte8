package de.kilobyte22.kibibyte.irc

import de.kilobyte22.kibibyte.auth.Account
import de.kilobyte22.kibibyte.chat.{Chat, CommandSender, User}
import org.pircbotx.{User => PircBotXUser}

class IrcUser(val backend: PircBotXUser, val bot: IrcBot) extends User {

  def nick = backend.getNick
  def username = backend.getLogin
  def host = Some(backend.getHostmask)
  def hostmask = s"$nick!$username@$host"

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
