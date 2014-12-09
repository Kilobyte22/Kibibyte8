package de.kilobyte22.kibibyte.auth

import de.kilobyte22.kibibyte.chat.Chat

import scala.collection.mutable.ArrayBuffer

trait Identity {

  def name: String

  def accounts: ArrayBuffer[Account]
  def checkPassword(password: String)

  val isExplicit: Boolean

  def hasPermission(perm: String, chat: Chat = null): Boolean = ???

  def makeExplicit(name: String)
}
