package de.kilobyte22.kibibyte.plugin

trait ServerPlugin extends Plugin {

  override def botAccess = super.botAccess.asInstanceOf[ServerAccess]

  /**
   * This plugin requires one of the specified backends in order to work
   * Set to empty if it runs on the default api
   */
  val onlyOn = List.empty[String]

  /**
   * Called right after the instance is created and set up. Do your
   */
  def enable()
  def disable()
}
