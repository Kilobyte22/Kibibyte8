package de.kilobyte22.kibibyte.message

import java.awt.Color

class FormattedString(text: String) {
  val part = new MessagePart(text)

  def color(r: Int, g: Int, b: Int) = color(new Color(r, g, b))
  def color(color: Color) = selfReturn(part.color = color)
  def background(r: Int, g: Int, b: Int) = background(new Color(r, g, b))
  def background(color: Color) = selfReturn(part.backgroundColor = color)
  def underline() = selfReturn(part.underline = true)
  def italic() = selfReturn(part.italic = true)
  def bold() = selfReturn(part.bold = true)

  private def selfReturn(ignored: Any*) = this
}

object FormattedString {

}
