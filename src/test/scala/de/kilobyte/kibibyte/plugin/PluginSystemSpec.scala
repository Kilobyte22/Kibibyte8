package de.kilobyte.kibibyte.plugin

import de.kilobyte22.kibibyte.plugin.{GlobalPlugin, PluginManager}
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

object TestPlugin {
  var loaded = false
  def reset() = loaded = false
}
class TestPlugin extends GlobalPlugin {
  override def load(): Unit = TestPlugin.loaded = true
  override def unload(): Unit = ()

  /**
   * Called right after the instance is created and set up. Do your initialization here
   */
  override def enable(): Unit = ???

  /**
   * Called right before the plugin is dropped from RAM
   */
  override def disable(): Unit = ???
}

class PluginSystemSpec extends FlatSpec with ShouldMatchers {
  "The Plugin System" should "load a Plugin" in {
    val pluginSystem = new PluginManager
    pluginSystem.load("testplugin", classOf[TestPlugin].getName)
    TestPlugin.loaded should be(true)
    TestPlugin.reset()
  }

  /*"The Plugin System" should "fail on nonexistant plugin class" in {
    val pluginSystem = new PluginManager
    a [ClassNotFoundException] should be thrownBy {
      pluginSystem.load("nope", "get rekt")
    }
  }*/
}
