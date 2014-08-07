package de.kilobyte22.kibibyte.command

import de.kilobyte22.kibibyte.chat.{CommandSender, Chat}
import de.kilobyte22.kibibyte.core.Kibibyte
import de.kilobyte22.optionparse.OptionParser

class CommandManager(center: CommandCenter, val namespace: String) {

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
  def commands(chat: Chat = null): Iterable[CommandInfo] = ???

  /**
   * Gets a command from the handler
   * @param name
   * @param chat
   * @return
   */
  def command(name: String, chat: Chat = null): CommandInfo = ???

  def +=(o: AnyRef) = {

  }

}
