package de.kilobyte22.kibibyte.plugin

/**
* Wrapper for an enabled plugin
*/
class PluginContainer(klass: Class[_], name: String) {

  private val ChatPluginClass = classOf[ChatPlugin]
  private val ServerPluginClass = classOf[ServerPlugin]
  private val GlobalPluginClass = classOf[GlobalPlugin]

  private var _enabled = false

  lazy val instance = klass.getSuperclass match {
    case ChatPluginClass => ChatContainer(klass.newInstance().asInstanceOf[ChatPlugin])
    case ServerPluginClass => ServerContainer(klass.newInstance().asInstanceOf[ServerPlugin])
    case GlobalPluginClass => GlobalContainer(klass.newInstance().asInstanceOf[GlobalPlugin])
  }

  /**
  * Enables the plugin
  */
  def enable(): Unit = {
    instance.plugin.enable()
    _enabled = true
  }

  /**
  * Disables the plugin
  */
  def disable(): Unit = {
    try {
      instance.plugin.disable()
    } catch {
      // TODO: Proper logging
      case ex: Exception => ex.printStackTrace()
    }
    _enabled = false
  }

  /**
  * Ensures the plugin is disabled when the container is garbage collected
  */
  override def finalize(): Unit = {
    if (_enabled) disable()
    super.finalize()
  }

}

class GlobalContainer(val plugin: GlobalPlugin)
object GlobalContainer {
  def apply(p: GlobalPlugin) = new GlobalContainer(p)
  def unapply(c: GlobalContainer): Option[GlobalPlugin] = Some(c.plugin)
}
class ServerContainer(override val plugin: ServerPlugin) extends GlobalContainer(plugin)
object ServerContainer {
  def apply(p: ServerPlugin) = new ServerContainer(p)
  def unapply(c: ServerContainer): Option[ServerPlugin] = Some(c.plugin)
}
class ChatContainer(override val plugin: ChatPlugin) extends ServerContainer(plugin)
object ChatContainer {
  def apply(p: ChatPlugin) = new ChatContainer(p)
  def unapply(c: ChatContainer): Option[ChatPlugin] = Some(c.plugin)
}
