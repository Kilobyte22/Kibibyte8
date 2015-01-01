package de.kilobyte22.kibibyte.plugin

class PluginContainer(klass: Class[_], name: String) {
  private val ChatPluginClass = classOf[ChatPlugin]
  private val ServerPluginClass = classOf[ServerPlugin]
  private val GlobalPluginClass = classOf[GlobalPlugin]
  val instance = klass.getSuperclass match {
    case ChatPluginClass =>
      if (klass.getField("MODULE$") != null)
        throw new Exception("For Chat Plugins you must use a class instead of an object.")
      ChatContainer(klass.newInstance().asInstanceOf[ChatPlugin])
    case ServerPluginClass =>
      if (klass.getField("MODULE$") != null)
        throw new Exception("For Server Plugins you must use a class instead of an object.")
      ServerContainer(klass.newInstance().asInstanceOf[ServerPlugin])
    case GlobalPluginClass =>
      val f = klass.getField("MODULE$")
      if (f == null)
        throw new Exception("For Global Plugins you must use a class instead of an object.")
      GlobalContainer(f.get().asInstanceOf[GlobalPlugin])
  }

  def load(): Unit = {

  }

}

case class GlobalContainer(plugin: GlobalPlugin)
case class ServerContainer(override val plugin: ServerPlugin) extends GlobalContainer(plugin)
case class ChatContainer(override val plugin: ChatPlugin) extends ServerContainer(plugin)
