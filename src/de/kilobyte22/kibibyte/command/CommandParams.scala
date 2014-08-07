package de.kilobyte22.kibibyte.command

import de.kilobyte22.kibibyte.chat.{CommandSender, Chat}

class CommandParams(val args: Iterable[String], val sender: CommandSender, val chat: Chat) {
  def sendChat(message: String) = chat match {
    case chat: Chat => chat.send(message)
    case _ => sender.sendMessage(message)
  }
}

object CommandParams {
  def apply(args: Iterable[String], sender: CommandSender, chat: Chat) =
    new CommandParams(args, sender, chat)
}