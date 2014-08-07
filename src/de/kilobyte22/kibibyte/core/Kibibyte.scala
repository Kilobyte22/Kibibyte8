package de.kilobyte22.kibibyte.core

import scala.collection.mutable

class Kibibyte {

  val events = mutable.Map.empty[Class, mutable.ArrayBuffer[AnyRef => Unit]]

  def onEvent[T](callback: T => Unit) = {
    // events(classOf[T])
    callback
  }
}

object Kibibyte {
  val Version = "8.0.0"
}