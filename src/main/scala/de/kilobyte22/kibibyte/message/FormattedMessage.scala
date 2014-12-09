package de.kilobyte22.kibibyte.message

import scala.collection.mutable.ArrayBuffer

object FormattedMessage {
  def color(s: String) = new FormattedString(s)
  implicit def formatString(s: String): FormattedString = new FormattedString(s)
  implicit def formatMessage(s: String): FormattedMessage = new FormattedMessage(new FormattedString(s))
  implicit def formatMessage(s: FormattedString): FormattedMessage = new FormattedMessage(s)
}

class FormattedMessage(strings: FormattedString*) {
  private val parts = ArrayBuffer.empty[FormattedString]
  def +(other: FormattedString) = {
    val m = new FormattedMessage(parts:_*)
    m += other
    m
  }
  def +=(other: FormattedString): Unit =
    parts += other

  def ++(other: FormattedMessage) =
    new FormattedMessage((parts ++ other.parts):_*)

  def ++=(other: FormattedMessage) =
    parts ++= other.parts
}

