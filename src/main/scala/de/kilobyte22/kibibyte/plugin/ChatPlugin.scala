package de.kilobyte22.kibibyte.plugin

/**
 * A plugin for a single chat
 */
trait ChatPlugin extends ServerPlugin {

  override def botAccess = super.botAccess.asInstanceOf[ChatAccess]


}
