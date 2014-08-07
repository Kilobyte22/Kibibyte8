package de.kilobyte22.kibibyte.irc

import org.pircbotx.Channel

class IrcChannel(backend: Channel, bot: IrcBot) extends IrcChat {
  def name = backend.getName
  def id = name.toLowerCase
  def join = bot.backend.

}
