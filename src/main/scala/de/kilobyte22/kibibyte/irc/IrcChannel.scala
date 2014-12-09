package de.kilobyte22.kibibyte.irc

import de.kilobyte22.kibibyte.chat.User
import org.pircbotx.Channel

class IrcChannel(backend: Channel, val bot: IrcBot) extends IrcChat(backend.getBot) {
  override def name = backend.getName
  override def id = name.toLowerCase
  override def join() = toChat(bot.backend.joinChannel(name))
  override def leave() = toChat(bot.backend.partChannel(backend))
  override def users = List.empty[IrcUser]

  override def kick(user: User, message: String = null) = user match {
    case u: IrcUser => toChat(bot.backend.kick(backend, u.backend, message))
    case _ => this
  }
  override def ban(user: User) = toChat(bot.backend.ban(backend, user.hostmask))
  override def senderFor(user: User) = new ChannelSender(this, user)

  private def toChat(u: Any) = this
}
