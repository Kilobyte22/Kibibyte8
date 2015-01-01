package de.kilobyte22.kibibyte.plugin

trait Plugin {
  private var _botAccess: BotAccess = null
  def botAccess: BotAccess = _botAccess
  final def setup(value: BotAccess) =
    if (_botAccess != null)
      throw new Exception("setup() can only be called once")
    else
      _botAccess = value
  def load()
  def unload()

  /**
   * Called right after the instance is created and set up. Do your initialization here
   */
  def enable()

  /**
   * Called right before the plugin is dropped from RAM
   */
  def disable()
}
