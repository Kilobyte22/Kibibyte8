package de.kilobyte22.kibibyte.irc

import de.kilobyte22.kibibyte.chat.Chat
import org.pircbotx.PircBotX

abstract class IrcChat(bot: PircBotX) extends Chat {
  override def send(message: String) = toChat(bot.sendNotice(id, message))

  private def toChat(u: Any) = this
}
