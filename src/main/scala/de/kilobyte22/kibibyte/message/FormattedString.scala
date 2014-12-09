package de.kilobyte22.kibibyte.message

import java.awt.Color

class FormattedString(val text: String) {
  val part = new MessagePart(text)

  def color(r: Int, g: Int, b: Int): FormattedString = color(new Color(r, g, b))
  def color(color: Color): FormattedString = selfReturn(part.color = color)
  def background(r: Int, g: Int, b: Int): FormattedString = background(new Color(r, g, b))
  def background(color: Color): FormattedString = selfReturn(part.backgroundColor = color)
  def underline() = selfReturn(part.underline = true)
  def italic() = selfReturn(part.italic = true)
  def bold() = selfReturn(part.bold = true)

  private def selfReturn(ignored: Any*) = this
}

object FormattedString {
  implicit def toPart(s: FormattedString): MessagePart = s.part
  implicit def formatMessage(s: FormattedString): FormattedMessage = new FormattedMessage(s)
}
