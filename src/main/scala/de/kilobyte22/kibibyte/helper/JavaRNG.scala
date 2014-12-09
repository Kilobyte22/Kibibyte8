package de.kilobyte22.kibibyte.helper

import java.util.Random

class JavaRNG(random: Random) extends RNG {
  override val randomness: Int = PseudoRandom
}
