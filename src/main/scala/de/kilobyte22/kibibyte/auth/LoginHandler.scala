package de.kilobyte22.kibibyte.auth

import de.kilobyte22.kibibyte.chat.{Bot, User}

trait LoginHandler {
  def accountFor(user: User): String
  def flushCache()
  def isCaching: Boolean
  def enable(bot: Bot)
  def disable(bot: Bot)
  def name: String
}
