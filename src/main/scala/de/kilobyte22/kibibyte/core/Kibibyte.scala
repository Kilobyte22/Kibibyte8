package de.kilobyte22.kibibyte.core

import com.google.common.eventbus.{Subscribe, EventBus}
import de.kilobyte22.kibibyte.chat.{Chat, CommandSender}
import de.kilobyte22.kibibyte.command.{CoreCommands, CommandManager, CommandCenter}
import de.kilobyte22.kibibyte.event.ChatMessageEvent

import scala.collection.mutable


class Kibibyte {

  val commandCenter = new CommandCenter
  val coreCommandManager = new CommandManager(commandCenter, "core")
  val eventBus = new EventBus
  var eventBusDebug = false

  commandCenter += coreCommandManager
  coreCommandManager += CoreCommands
  eventBus.register(this)

  def runCommand(command: String, sender: CommandSender, chat: Chat = null) =
    commandCenter.run(command, sender, chat)

  def runCommand(command: String): Unit =
    runCommand(command, Core.ConsoleUser)

  @Subscribe
  def onMessage(event: ChatMessageEvent) {
    if (event.message.startsWith("##")) {
      commandCenter.runLine(event.message.substring(2), event.chat.senderFor(event.user), event.chat)
    }
  }
  @Subscribe
  def onEverything(event: Any) {
    if (eventBusDebug)
      println(event)
  }
}

object Kibibyte {
  val Version = "8.0.0"
}