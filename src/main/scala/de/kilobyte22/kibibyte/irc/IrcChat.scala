package de.kilobyte22.kibibyte.irc

import de.kilobyte22.kibibyte.api.chat.Chat
import de.kilobyte22.kibibyte.message.FormattedMessage
import org.pircbotx.PircBotX

abstract class IrcChat(bot: PircBotX) extends Chat {
  override def send(message: FormattedMessage) = toChat(bot.sendNotice(id, message.format(IrcFormatter)))

  private def toChat(u: Any) = this
}
