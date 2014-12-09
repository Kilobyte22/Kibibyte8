package de.kilobyte22.kibibyte.message

import java.awt.Color

import org.scalatest._
import org.scalatest.matchers.ClassicMatchers

object TestSimpleFormatter extends MessageFormatter {
  //override def formatColor(foreground: Color, background: Color): String = f"=c(#${foreground.getRGB}%06X, #${background.getRGB}%06X)"
  override def formatBold(s: String): String = s"%b{$s}"

  override def formatItalic(s: String): String = s"%i{$s}"

  override def formatUnderline(s: String): String = s"%u{$s}"

  override def formatColor(s: String, fg: Color, bg: Color): String = f"%%c(#${fg.getRGB & 0xFFFFFF}%06X, #${bg.getRGB & 0xFFFFFF}%06X){$s%s}"
}



class FormattedMessageSpec extends FlatSpec with ClassicMatchers {
  "A message" should "be bolded" in {
    val f = FormattedMessage.format("text").bold().format(TestSimpleFormatter)
    if (f != "%b{%c(#000000, #FFFFFF){text}}")
      fail("Bold formatting did not work")
  }

  "A message" should "be italic" in {
    if (FormattedMessage.format("text").italic().format(TestSimpleFormatter) != "%i{%c(#000000, #FFFFFF){text}}")
      fail("Italic formatting did not work")
  }

  "A message" should "be underlined" in {
    if (FormattedMessage.format("text").underline().format(TestSimpleFormatter) != "%u{%c(#000000, #FFFFFF){text}}")
      fail("Underline formatting did not work")
  }
}
