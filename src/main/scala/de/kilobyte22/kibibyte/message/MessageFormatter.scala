package de.kilobyte22.kibibyte.message

import java.awt.Color

trait MessageFormatter {
  def formatBold(s: String): String
  def formatItalic(s: String): String
  def formatUnderline(s: String): String
  def formatColor(s: String, fg: Color, bg: Color): String

  def format(msg: FormattedMessage) = {
    msg.parts.map((el) => {
      var tmp = el.text
      tmp = formatColor(tmp, el.color, el.backgroundColor)
      if (el.bold) tmp = formatBold(tmp)
      if (el.italic) tmp = formatItalic(tmp)
      if (el.underline) tmp = formatUnderline(tmp)
      tmp
    }).mkString
  }
}
