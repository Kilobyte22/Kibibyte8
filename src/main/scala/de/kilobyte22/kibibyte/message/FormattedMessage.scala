package de.kilobyte22.kibibyte.message

import scala.collection.mutable.ArrayBuffer

object FormattedMessage {
  def format(s: String) = new FormattedString(s)
  implicit def formatString(s: String): FormattedString = new FormattedString(s)
  implicit def formatMessage(s: String): FormattedMessage = new FormattedMessage(new FormattedString(s))
}

class FormattedMessage(strings: MessagePart*) {
  protected[message] val parts = ArrayBuffer.empty[MessagePart]
  strings.foreach((el) => parts += el)
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

  def format(f: MessageFormatter) = f.format(this)
}

