package de.kilobyte22.kibibyte.api.auth

import de.kilobyte22.kibibyte.api.chat.{Bot, User}
import de.kilobyte22.kibibyte.auth.Account

trait LoginHandler {
  def getAccountsForUser(user: User): List[Account]
  def enable(bot: Bot)
  def disable(bot: Bot)
}
