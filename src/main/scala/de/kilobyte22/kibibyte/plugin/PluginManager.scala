package de.kilobyte22.kibibyte.plugin

import de.kilobyte22.kibibyte.chat.{Chat, Bot}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/*
* Manages loading of Plugins
*/
class PluginManager {

  /**
  * Map of all loaded global plugins
  */
  val plugins = mutable.Map.empty[String, PluginContainer]

  /**
  * Map of all loaded chat plugins
  */
  val chatPlugins = mutable.WeakHashMap.empty[Chat, ArrayBuffer[PluginContainer]]
  /**
  * Map of all loaded server plugins
  */
  val serverPlugins = mutable.WeakHashMap.empty[Bot, ArrayBuffer[PluginContainer]]
  /**
  * Map of all loadable plugins
  */
  val availablePlugins = mutable.Map.empty[String, PluginMetadata]

  /**
  * Loads a plugin by name
  */
  def load(name: String) = {

  }

  /**
  * Loads a plugin from class under a specified name
  */
  def load(name: String, clazz: String) = {
    //val p = new PluginContainer(Class.forName(clazz), name)
    //plugins(name) = p
    //p.load()
  }
  
  /**
  * Enables a global plugin
  */
  def enable(name: String) = {
    plugins(name).enable()
  }

  /**
  * Disables a global plugin
  */
  def disable(name: String) = ???
}
