package de.kilobyte22.kibibyte.plugin

import de.kilobyte22.kibibyte.chat.{Chat, Bot}

import scala.collection.mutable

class PluginManager(thing: PluginCarrier) {
  val plugins = mutable.Map.empty[String, PluginContainer]
  def load(name: String) = {

  }
  def load(name: String, clazz: String) = {
    val p = new PluginContainer(Class.forName(clazz), name)
    plugins(name) = p
    p.
  }

  def enable(name: String) = ???
  def disable(name: String) = ???
}

abstract class PluginCarrier
sealed case class ForGlobal() extends PluginCarrier
sealed case class ForServer(bot: Bot) extends PluginCarrier
sealed case class ForChat(chat: Chat) extends PluginCarrier