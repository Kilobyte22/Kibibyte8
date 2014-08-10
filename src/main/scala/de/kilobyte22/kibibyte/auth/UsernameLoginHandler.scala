package de.kilobyte22.kibibyte.auth

import de.kilobyte22.kibibyte.chat.{User, Bot}

object UsernameLoginHandler extends LoginHandler {

  override def accountFor(user: User) = user.host match {
    case Some(host: String) => s"${user.username}@$host"
    case None => user.username
  }

  override def disable(bot: Bot) = Unit

  override def flushCache() = Unit

  override def name = "username"

  override def isCaching = true

  override def enable(bot: Bot) = Unit
}
