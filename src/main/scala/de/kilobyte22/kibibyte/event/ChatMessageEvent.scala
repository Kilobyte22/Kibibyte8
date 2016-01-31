package de.kilobyte22.kibibyte.event

import de.kilobyte22.kibibyte.api.chat.{Bot, Chat, User}

class ChatMessageEvent(val bot: Bot, val user: User, val chat: Chat, val message: String) extends BotEvent with UserEvent with ChatEvent
