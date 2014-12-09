package de.kilobyte22.kibibyte.core

import de.kilobyte22.kibibyte.auth.LoginHandler
import de.kilobyte22.kibibyte.chat.Bot

import scala.collection.mutable

class Server(val bot: Bot) {
  def loginHandler: LoginHandler = ???
}

object Server {

  private val servers = mutable.WeakHashMap.empty[Bot, Server]

  def serverFor(bot: Bot) = if (servers.contains(bot))
    servers(bot)
  else
    null
}
