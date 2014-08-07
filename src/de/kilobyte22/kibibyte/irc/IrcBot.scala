package de.kilobyte22.kibibyte.irc

import de.kilobyte22.config.ConfigElement
import de.kilobyte22.kibibyte.chat.{User, Chat, Bot}
import de.kilobyte22.kibibyte.core.Kibibyte
import org.pircbotx.PircBotX

import scala.collection.mutable

class IrcBot(val id: String, cfg: ConfigElement) extends Bot {

  val users = mutable.Map.empty[String, IrcUser]
  val chats = mutable.Map.empty[String, IrcChat]
  val backend = new PircBotX
  backend.setName(cfg.first("nick").getString(0))
  backend.setLogin("kibibyte")
  backend.setVersion(s"Kibibyte, version ${Kibibyte.Version}");
  {
    val connect = cfg.first("connect")
    backend.connect(connect.getString(0), connect.getInt(1))
  }

  override def getUser(name: String): Option[IrcUser] = {
    val name2 = name.toLowerCase
    if (users.contains(name2))
      Some(users(name2))
    else {
      val ret = new IrcUser(name, this)
      users(name2) = ret
      Some(ret)
    }
  }

  override def getChat(name: String): Option[Chat] = ???
}
