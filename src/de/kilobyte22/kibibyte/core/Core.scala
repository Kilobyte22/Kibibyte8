package de.kilobyte22.kibibyte.core

import de.kilobyte22.kibibyte.chat.{CommandSender, User, Chat, Bot}

object Core {

  object ConsoleUser extends CommandSender {
    private var _verbose = true
    override def hasPermission(name: String, chat: Chat = null) = true

    override def canVerbose: Boolean = true

    override def sendError(message: String): CommandSender = {
      message.split("\n").foreach(line => println(s"\u001B[31m[CMD] $line\u001B[0m"))
      this
    }

    override def verbose_=(value: Boolean): Boolean = {
      _verbose = value // WTH IS THIS Unit
      value
    }

    override def verbose: Boolean = _verbose

    override def sendMessage(message: String): CommandSender = {
      message.split("\n").foreach(line => println(s"[CMD] $line"))
      this
    }

    override def commandNotFound(command: String) = sendError(s"Command $command not found")
  }

}
