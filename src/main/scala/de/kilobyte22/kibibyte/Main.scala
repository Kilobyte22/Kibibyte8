package de.kilobyte22.kibibyte

import java.io.File

import de.kilobyte22.config.ConfigFile
import de.kilobyte22.kibibyte.core.Kibibyte
import de.kilobyte22.kibibyte.irc.IrcBot

import scala.collection.mutable.ArrayBuffer
import scala.collection.JavaConversions._

object Main {
  /**
  * Main entry point for kibibyte. Set this as main method when including 
  * kibi as library in your plugin project
  */
  def main(args: Array[String]) {
    val f = new File("kibibyte.cfg")

    if (!f.exists()) {
      // TODO: Copy example file there
      f.createNewFile()

    }
    val bot = new Kibibyte


    val file = new ConfigFile(f)
    file.load()

    bot.eventBusDebug = file.has("eventbusdebug")
    val ircs = ArrayBuffer.empty[IrcBot]

    println("Connecting...")

    file.getOptions("irc").foreach(el => {
      val irc = new IrcBot(el.getString(0), el.getSub, bot)
    })

    println("Connected.")

    for (ln <- io.Source.stdin.getLines())
      bot.runCommand(ln)
  }
}
