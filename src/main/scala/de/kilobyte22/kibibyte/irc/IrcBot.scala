package de.kilobyte22.kibibyte.irc

import de.kilobyte22.config.ConfigElement
import de.kilobyte22.kibibyte.auth.LoginHandler
import de.kilobyte22.kibibyte.chat.{User, Chat, Bot}
import de.kilobyte22.kibibyte.core.Kibibyte
import de.kilobyte22.kibibyte.event.ChatMessageEvent
import org.pircbotx.PircBotX
import org.pircbotx.hooks.ListenerAdapter
import org.pircbotx.hooks.events.MessageEvent

import scala.collection.mutable
import scala.collection.JavaConversions._

class IrcBot(val id: String, cfg: ConfigElement, kibibyte: Kibibyte) extends Bot {

  val users = mutable.Map.empty[String, IrcUser]
  val chats = mutable.Map.empty[String, IrcChat]
  val backend = new PircBotX

  backend.setName(cfg.first("nick").getString(0))
  backend.setLogin("kibibyte")
  backend.setVersion(s"Kibibyte, version ${Kibibyte.Version}")
  backend.setVerbose(cfg.has("verbose"))
  backend.getListenerManager.addListener(new IrcListener(kibibyte, this))

  {
    val connect = cfg.first("connect")
    backend.connect(connect.getString(0), connect.getInt(1))
  }
  cfg.getOptions("channel").foreach(el => backend.joinChannel(el.getString(0)))

  override def getUser(name: String): Option[IrcUser] = {
    val name2 = name.toLowerCase
    if (users.contains(name2))
      Some(users(name2))
    else {
      val ret = new IrcUser(backend.getUser(name), this)
      users(name2) = ret
      Some(ret)
    }
  }

  override def getChat(name: String): Option[Chat] = {
    val name2 = name.toLowerCase
    if (chats.contains(name2))
      Some(chats(name2))
    else {
      val ret = if (name.startsWith("#"))
        new IrcChannel(backend.getChannel(name), this)
      else
        null
      chats(name2) = ret
      Some(ret)
    }
  }
}

class IrcListener(kibi: Kibibyte, bot: IrcBot) extends ListenerAdapter {
  override def onMessage(event: MessageEvent[Nothing]) {
    kibi.eventBus.post(new ChatMessageEvent(bot, bot.getUser(event.getUser.getNick).get, bot.getChat(event.getChannel.getName).get, event.getMessage))
  }
}