package de.kilobyte22.kibibyte

import java.io.File

import de.kilobyte22.config.ConfigFile

object Main extends App {
  val f = new File("kibibyte.cfg")

  if (!f.exists()) {
    f.createNewFile()

  }


  val file = new ConfigFile(f)
}
