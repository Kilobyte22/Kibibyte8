package de.kilobyte22.kibibyte.plugin

import de.kilobyte22.kibibyte.chat.{Chat, Bot}
import de.kilobyte22.kibibyte.core.Kibibyte

class ChatAccess(kibibyte: Kibibyte, bot: Bot, val chat: Chat) extends ServerAccess(kibibyte, bot)
