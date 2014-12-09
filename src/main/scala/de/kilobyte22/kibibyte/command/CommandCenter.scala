package de.kilobyte22.kibibyte.command

import de.kilobyte22.kibibyte.chat.{CommandSender, Chat}
import de.kilobyte22.optionparse.OptionParser

import scala.collection.mutable.{ListBuffer, ArrayBuffer}
import scala.collection.mutable

class CommandCenter {

  private val handlers = mutable.Map.empty[String, CommandManager]
  private var commandCache: List[CommandInfo] = null
  private val chatCache = mutable.WeakHashMap.empty[Chat, List[CommandInfo]]
  private val nameCache = mutable.Map.empty[String, List[CommandInfo]]
  private val nameChatCache = mutable.WeakHashMap.empty[Chat, mutable.Map[String, List[CommandInfo]]]

  /**
   * Gets all commands
   * @return all commands
   */
  def commands: Iterable[CommandInfo] = commands()

  def commands(chat: Chat = null): Iterable[CommandInfo] =
    if (chat == null) {
      if (commandCache == null) {
        val buf = ListBuffer.empty[CommandInfo]
        handlers.values.foreach(el => buf ++= el.commands)
        commandCache = buf.toList
      }
      commandCache
    } else {
      val data = if (chatCache.contains(chat))
        chatCache(chat)
      else {
        val buf = ListBuffer.empty[CommandInfo]
        handlers.values.foreach(el => buf ++= el.commands(chat))
        val d = buf.toList
        chatCache(chat) = d
        d
      }
      data
    }

  def commands(name: String): Iterable[CommandInfo] = commands(name, null)

  def commands(name: String, chat: Chat): Iterable[CommandInfo] = if (chat == null) {
    if (!nameCache.contains(name)) {
      val ret_ = commands()
      val ret = ret_.filter(_.name == name)
      if (ret.size > 0)
        nameCache(name) = ret.toList
      ret
    } else nameCache(name)
  } else {
    if (!nameChatCache.contains(chat))
      nameChatCache(chat) = mutable.Map.empty[String, List[CommandInfo]]
    val c = nameChatCache(chat)
    if (!c.contains(name)) {
      val ret = commands(chat).filter(_.name == name)
      if (ret.size > 1)
        c(name) = ret.toList
      ret
    } else c(name)
  }

  def handler(namespace: String): CommandManager = ???

  def +=(h: CommandManager) = {
    handlers(h.namespace) = h
    invalidateCache()
  }

  def -=(h: CommandManager) = {
    handlers.remove(h.namespace)
    invalidateCache()
  }

  def invalidateCache() {
    commandCache = null
    chatCache.clear()
    nameCache.clear()
  }

  /**
   * Runs a command
   * @param namespace the command namespace, can be null if command is unique
   * @param name Name of the command
   * @param args arguments for the command
   * @param sender the origin of the command
   * @param chat the chat this command was issued in. Can be null if it was issued for example from command line
   */
  def run(namespace: String, name: String, args: Array[String], sender: CommandSender, chat: Chat, pipeData: String): String = {
    val cmds = if (namespace == null)
      commands(name, chat)
    else
      handler(namespace).command(name, chat) match {
        case command: CommandInfo => List(command)
        case _ => List.empty[CommandInfo]
      }
    val params = CommandParams(args, sender, chat, pipeData)
    cmds.size match {
      case 0 => sender.commandNotFound(name)
      case x: Int if x > 1 => sender.sendError("Multiple matching commands found") // TODO: Add details to error
      case _ => {
        val cmd = cmds.head
        try {
          cmd.run(params)
        } catch {
          case ex: Throwable => ex.printStackTrace()
        }
      }
    }
    params.buffer
  }

  /**
   * runs a command by just having the command line
   * @param string the input string
   * @param sender The sender of the command
   * @param chat
   */
  def run(string: String, sender: CommandSender, chat: Chat, pipeData: String = ""): String = {
    val splitted = OptionParser.splitArgs(string)
    val command = splitted.head
    val args = splitted.tail
    val (prefix, name) = if (command.contains(":")) {
      val parts = command.split(":")
      (parts.init.mkString(":"), parts.last)
    } else (null, command)
    run(prefix, name, args, sender, chat, pipeData)
  }

  def runLine(string: String, sender: CommandSender, chat: Chat, pipeData: String = "") {
    val message = run(string, sender, chat, pipeData) // CommandParser.run(string).run(sender, chat, this, pipeData)
    if (message != "") chat match {
      case chat: Chat => chat.send(message)
      case _ => sender.sendMessage(message)
    }
  }


}
