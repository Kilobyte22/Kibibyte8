package de.kilobyte22.kibibyte.plugin

import de.kilobyte22.kibibyte.chat.{Chat, Bot}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class PluginManager {
  val plugins = mutable.Map.empty[String, PluginContainer]
  val chatPlugins = mutable.WeakHashMap.empty[Chat, ArrayBuffer[PluginContainer]]
  val serverPlugins = mutable.WeakHashMap.empty[Bot, ArrayBuffer[PluginContainer]]

  def load(name: String) = {

  }
  def load(name: String, clazz: String) = {
    val p = new PluginContainer(Class.forName(clazz), name)
    plugins(name) = p
    p.load()
  }

  def enable(name: String) = {
    plugins(name).enable()
  }
  def disable(name: String) = ???
}