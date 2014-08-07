package de.kilobyte22.kibibyte.chat

trait CommandSender {
  def hasPermission(name: String, chat: Chat = null): Boolean
  def canVerbose: Boolean
  def verbose: Boolean
  def verbose_=(value: Boolean): Boolean
  def sendMessage(message: String): CommandSender
  def sendError(message: String): CommandSender
  def commandNotFound(command: String): CommandSender
}
