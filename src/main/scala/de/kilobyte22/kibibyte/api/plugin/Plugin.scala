package de.kilobyte22.kibibyte.api.plugin

import de.kilobyte22.kibibyte.api.chat.{Bot, Chat}

trait Plugin {
  /**
    * Called right before the plugin will be fully unloaded
    *
    * This will always be called directly prior to process termination.
    * Please note that the bot may still be fully running at this point,
    * as plugin and bot do not necessarily have to run in the same process,
    * But might as well be talking through some IPC. This will be handled
    * transparently by the API.
    */
  def unload()
}

trait ChatPlugin extends Plugin {
  /**
    * Called when the plugin is to be loaded
    *
    * This method will only ever be called a second time with the same parameter after
    * the disable method of the previous instance has been called
    * @param chat the chat this plugin is loaded for
    * @return the instance of the plugin
    */
  def enable(chat: Chat): PluginInstance
}

trait ServerPlugin extends Plugin {
  /**
    * Called when the plugin is to be loaded
    *
    * This method will only ever be called a second time with the same parameter after
    * the disable method of the previous instance has been called
    * @param server the server this plugin is loaded for
    * @return the instance of the plugin
    */
  def enable(server: Bot): PluginInstance
}

trait GlobalPlugin extends Plugin {
  /**
    * Called when the plugin is to be loaded
    *
    * This method will only ever be called a second time after
    * the disable method of the previous instance has been called
    * @return the instance of the plugin
    */
  def enable(): PluginInstance
}