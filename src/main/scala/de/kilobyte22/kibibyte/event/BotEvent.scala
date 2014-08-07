package de.kilobyte22.kibibyte.event

import de.kilobyte22.kibibyte.chat.Bot

trait BotEvent extends Event {
  val bot: Bot
}
