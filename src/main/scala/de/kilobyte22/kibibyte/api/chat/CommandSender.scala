package de.kilobyte22.kibibyte.api.chat

import de.kilobyte22.kibibyte.message.FormattedMessage

trait CommandSender {
  def hasPermission(name: String, chat: Chat = null): Boolean
  def canVerbose: Boolean
  def verbose: Boolean
  def verbose_=(value: Boolean): Boolean
  def sendMessage(message: FormattedMessage): CommandSender
  def sendError(message: FormattedMessage): CommandSender
  def commandNotFound(command: String): CommandSender
}
