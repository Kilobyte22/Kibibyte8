package de.kilobyte22.kibibyte.irc

import de.kilobyte22.kibibyte.chat.{Chat, CommandSender, User}

class ChannelSender(chat: IrcChannel, user: User) extends CommandSender {
  override def hasPermission(name: String, chat: Chat): Boolean = ???

  override def sendError(message: String): CommandSender = user.sendError(message)

  override def verbose_=(value: Boolean): Boolean = ???

  override def verbose: Boolean = false

  override def canVerbose: Boolean = false

  override def commandNotFound(command: String): CommandSender = this // No command missing message in channel

  override def sendMessage(message: String): CommandSender = user.sendMessage(message)
}
