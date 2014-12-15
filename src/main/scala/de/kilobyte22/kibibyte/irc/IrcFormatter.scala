package de.kilobyte22.kibibyte.irc

import java.awt.Color

import de.kilobyte22.kibibyte.message.MessageColorReducer

object IrcFormatter extends MessageColorReducer[String] {
  override val colors: List[(Color, String)] = List((Color.BLACK, ""))

  override def formatColored(s: String, fg: String, bg: String): String = s // TODO: too lazy for colors right now

  override def formatItalic(s: String): String = s"\u0002$s\u0002"

  override def formatUnderline(s: String): String = s"\u001F$s\u001F"

  override def formatBold(s: String): String = s"\u001D$s\u001D"
}
