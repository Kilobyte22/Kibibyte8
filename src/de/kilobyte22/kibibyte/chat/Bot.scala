package de.kilobyte22.kibibyte.chat

trait Bot {
  def id: String
  def getUser(name: String): Option[User]
  def getChat(name: String): Option[Chat]

}
