package de.kilobyte22.kibibyte.helper

abstract class RNG {
  val PseudoRandom = 0
  val Random = 1
  val TrulyRandom = 2

  val randomness: Int
}

object RNG {
  implicit def toRNG(in: java.util.Random): RNG = new JavaRNG(in)
}
