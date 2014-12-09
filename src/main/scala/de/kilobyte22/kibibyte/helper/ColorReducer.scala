package de.kilobyte22.kibibyte.helper

import java.awt.Color
import math.sqrt

abstract class ColorReducer {
  val colors: List[(Color, String)]

  def getNearest(color: Color) = {
    val diffs: List[(Double, String)] = colors.map((c) => (distance(c._1, color), c._2))
    diffs.sortWith((el1, el2) => el1._1 < el2._1).head._2
  }
  
  def distance(color1: Color, color2: Color) = {
    val hsbvals1 = Array(0f, 0f, 0f)
    val hsbvals2 = Array(0f, 0f, 0f)
    
    Color.RGBtoHSB(color1.getRed, color1.getGreen, color1.getBlue, hsbvals1)
    Color.RGBtoHSB(color2.getRed, color2.getGreen, color2.getBlue, hsbvals2)

    sqrt(sq(hsbvals1(0) - hsbvals2(0)) + sq(hsbvals1(1) - hsbvals2(1)) + sq(hsbvals1(2) - hsbvals2(2)))
  }

  val boldEnable: String
  val boldDisable: String
  val italicEnable: String
  val italicDisable: String
  val underlineEnable: String
  val underlineDisable: String

  def formatColor(foreground: String, background: String): String


  private def sq(f: Float) = f * f


}
