package de.kilobyte22.kibibyte.plugin

class PluginContainer(klass: Class[_], name: String) {

  private val ChatPluginClass = classOf[ChatPlugin]
  private val ServerPluginClass = classOf[ServerPlugin]
  private val GlobalPluginClass = classOf[GlobalPlugin]
  val instance = klass.getSuperclass match {
    case ChatPluginClass => ChatContainer(klass.newInstance().asInstanceOf[ChatPlugin])
    case ServerPluginClass => ServerContainer(klass.newInstance().asInstanceOf[ServerPlugin])
    case GlobalPluginClass => GlobalContainer(klass.newInstance().asInstanceOf[GlobalPlugin])
  }

  def load(): Unit = {
    instance.plugin.load()
  }

  def enable(): Unit = {
    instance.plugin.enable()
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
