package de.kilobyte22.kibibyte.core

import de.kilobyte22.kibibyte.chat.{Chat, CommandSender}
import de.kilobyte22.kibibyte.message.FormattedMessage

object Core {

  object ConsoleUser extends CommandSender {
    private var _verbose = true
    override def hasPermission(name: String, chat: Chat = null) = true

    override def canVerbose: Boolean = true

    override def sendError(message: FormattedMessage): CommandSender = {
      message.toString.split("\n").foreach(line => println(s"\u001B[31m[CMD] ${line}\u001B[0m"))
      this
    }

    override def verbose_=(value: Boolean): Boolean = {
      _verbose = value
      value
    }

    override def verbose: Boolean = _verbose

    override def sendMessage(message: FormattedMessage): CommandSender = {
      message.toString.split("\n").foreach(line => println(s"[CMD] $line"))
      this
    }

    override def commandNotFound(command: String) = sendError(s"Command $command not found")
  }

}
