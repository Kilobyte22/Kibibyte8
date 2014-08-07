package de.kilobyte22.kibibyte

import java.io.File

import de.kilobyte22.config.ConfigFile
import de.kilobyte22.kibibyte.core.Kibibyte
import de.kilobyte22.kibibyte.irc.IrcBot

import scala.collection.mutable.ArrayBuffer
import scala.collection.JavaConversions._

object Main {
  def main(args: Array[String]) {
    val f = new File("kibibyte.cfg")

    if (!f.exists()) {
      f.createNewFile()

    }
    val bot = new Kibibyte


    val file = new ConfigFile(f)
    file.load()

    bot.eventBusDebug = file.has("eventbusdebug")
    val ircs = ArrayBuffer.empty[IrcBot]

    file.getOptions("irc").foreach(el => {
      val irc = new IrcBot(el.getString(0), el.getSub, bot)
    })

    for (ln <- io.Source.stdin.getLines())
      bot.runCommand(ln)
  }
}
