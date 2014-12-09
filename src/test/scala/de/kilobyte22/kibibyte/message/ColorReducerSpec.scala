package de.kilobyte22.kibibyte.message

import java.awt.Color

import org.scalatest._

object TestSimpleColorReducer extends MessageColorReducer[String] {
  override val colors: List[(Color, String)] = List(
    (Color.RED, "red"),
    (Color.YELLOW, "yellow"),
    (Color.GREEN, "green")
  )

  override def formatColored(s: String, fg: String, bg: String): String = ???

  override def formatItalic(s: String): String = ???

  override def formatUnderline(s: String): String = ???

  override def formatBold(s: String): String = ???
}

class ColorReducerSpec extends FlatSpec {
  "A color" should "be mapped down to whats possible" in {
    val testColors = List((Color.RED, "red"), (new Color(200, 0, 0), "red"), (Color.GREEN.darker(), "green"))
    testColors.foreach((c) => {
      if (TestSimpleColorReducer.getNearest(c._1) != c._2)
        fail(s"Did not map ${c._1.toString} to ${c._2}")
    })
  }
}
