package de.kilobyte22.kibibyte.command

import de.kilobyte22.kibibyte.chat.{CommandSender, Chat}

class CommandParams(val args: CommandArgs, val sender: CommandSender, val chat: Chat, val input: String = "") {
  private var _buffer = ""
  def buffer = _buffer

  def sendChat(message: String) = {
    if (_buffer.length != 0)
      _buffer += "\n"
    _buffer += message
  }
}

object CommandParams {
  def apply(args: Array[String], sender: CommandSender, chat: Chat, input: String = "") =
    new CommandParams(CommandArgs(args), sender, chat, input)
}