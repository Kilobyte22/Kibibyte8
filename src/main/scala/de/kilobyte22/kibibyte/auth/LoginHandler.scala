package de.kilobyte22.kibibyte.auth

import de.kilobyte22.kibibyte.chat.{Bot, User}

trait LoginHandler {
  def getAccountsForUser(user: User): List[Account]
  def enable(bot: Bot)
  def disable(bot: Bot)
}
