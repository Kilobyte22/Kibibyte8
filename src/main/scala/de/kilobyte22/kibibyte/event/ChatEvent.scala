package de.kilobyte22.kibibyte.event

import de.kilobyte22.kibibyte.api.chat.Chat

trait ChatEvent {
  val chat: Chat
}
