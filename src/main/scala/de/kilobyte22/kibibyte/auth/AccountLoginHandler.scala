package de.kilobyte22.kibibyte.auth

import de.kilobyte22.kibibyte.chat.{User, Bot}
import de.kilobyte22.kibibyte.command.{CommandParams, command}

import scala.collection.mutable

object AccountLoginHandler extends LoginHandler {

  val accounts = mutable.WeakHashMap.empty[User, String]

  override def accountFor(user: User) = if (accounts.contains(user)) accounts(user) else null

  override def disable(bot: Bot) = Unit

  override def flushCache() = Unit

  override def name = "account"

  override def isCaching = true

  override def enable(bot: Bot) = Unit

  @command def login(params: CommandParams) = Unit
}
