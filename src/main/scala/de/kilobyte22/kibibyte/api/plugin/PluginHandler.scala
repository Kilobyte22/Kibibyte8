package de.kilobyte22.kibibyte.api.plugin

import de.kilobyte22.kibibyte.plugin.PluginMetadata

/**
  * Handles loading plugins
  */
trait PluginHandler {
  def providedPlugins(): Seq[PluginMetadata]
  def loadPlugin(id: String): PluginInstance
}
