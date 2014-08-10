package de.kilobyte22.kibibyte.auth

import de.kilobyte22.kibibyte.chat.Chat
import de.kilobyte22.kibibyte.notifications.NotificationSystem

class Account {
  def name: String = ???
  def hasPermission(perm: String, chat: Chat = null): Boolean = ???
  def checkPassword(password: String) = ???
}
