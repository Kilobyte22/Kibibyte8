package de.kilobyte22.kibibyte.command

import de.kilobyte22.kibibyte.chat.{CommandSender, Chat}
import de.kilobyte22.kibibyte.core.Kibibyte
import de.kilobyte22.optionparse.OptionParser

import scala.collection.mutable

class CommandManager(center: CommandCenter, val namespace: String) {

  val commandStore = mutable.Map.empty[String, CommandInfo]

  /**
   * Returns all commands this handler knows
   * @return all commands this handler knows
   */
  def commands: Iterable[CommandInfo] = commands()

  /**
   * Returns all commands usable in a certain [[Chat]]
   * @param chat the [[Chat]] this command can be used in
   * @return all commands usable in a certain [[Chat]]
   */
  def commands(chat: Chat = null): Iterable[CommandInfo] =
    commandStore.values // Add per-chat commands

  /**
   * Gets a command from the handler
   * @param name
   * @param chat
   * @return
   */
  def command(name: String, chat: Chat = null): CommandInfo = ???

  def +=(o: AnyRef) =
    o.getClass.getMethods filter(_.isAnnotationPresent(classOf[command])) foreach(m => {
      println(s"Found method $m")
      val annot = m.getAnnotation(classOf[command])
      val name = if (annot.name() == "") m.getName else annot.name()

      commandStore += ((name, new CommandInfo(o, MethodHandles.lookup.unreflect(m), name, namespace)))
    })

}