package de.kilobyte22.kibibyte.plugin

// TODO: Make load/unload methods on the companion object

/**
* Base for a plugin
*/
trait Plugin {
  private var _botAccess: BotAccess = null
  
  /**
  * Getter for the [[BotAccess]]
  */
  def botAccess: BotAccess = _botAccess
  final def setup(value: BotAccess) =
    if (_botAccess != null)
      throw new Exception("setup() can only be called once")
    else
      _botAccess = value
  
  /**
  * Called right after the instance is created and set up. Do your initialization here
  */
  def enable()

  /**
   * Called right before the plugin is disabled and the 
   * class instance is dropped from ram
   */
  def disable()
}
