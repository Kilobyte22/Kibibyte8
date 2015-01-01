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
}
