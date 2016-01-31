package de.kilobyte22.kibibyte.command

import de.kilobyte22.kibibyte.api.chat.{Chat, CommandSender}

object CommandParser {
  def run(data: String) = {
    val args = data.split(" ").filter(!_.isEmpty)
    new CommandResult(args.head, args.tail)
  }
}

class CommandResult(command: String, args: Seq[String]) {
  def run(sender: CommandSender, chat: Chat, center: CommandCenter, pipeData: String) = center.run(command, sender, chat, pipeData)
}

// TODO: Fix this for piping support

/*
import de.kilobyte22.kibibyte.chat.{Chat, CommandSender}

import scala.util.parsing.combinator.RegexParsers

object CommandParser extends RegexParsers {
  def commandRegex = """(?:[^|"]+|"(?:\\"|[^"])*")+""".r

  def pipeOrCommand: Parser[Element] = pipe | command

  def pipe: Parser[Element] = (command <~ "|") ~ pipeOrCommand ^^ { el => Pipe(el._1, el._2) }

  def command: Parser[Element] = commandRegex ^^ { data => Command(data.trim) }

  def run(data: String) = parseAll(pipeOrCommand, data) match {
    case CommandParser.Success(x: AnyRef, _) => x
    case CommandParser.Failure(x, _) => throw new Exception(x)
  }
}

abstract class Element {
  def run(sender: CommandSender, chat: Chat, center: CommandCenter, pipeData: String): String
}

case class Command(data: String) extends Element {
  override def run(sender: CommandSender, chat: Chat, center: CommandCenter, pipeData: String) =
    center.run(data, sender, chat, pipeData)
  override def toString =
    "Command(" + data.replace("\\", "\\\\").replace("\"", "\\\"") + ")"
}
case class Pipe(_1: Element, _2: Element) extends Element {
  override def run(sender: CommandSender, chat: Chat, center: CommandCenter, pipeData: String) = {
    val data = _1.run(sender, chat, center, pipeData)
    if (data.length == 0)
      "[" + _1.toString + " had no output. Interrupting chain.]"
    else
      _2.run(sender, chat, center, data)
  }
  override def toString =
    s"Pipe(${_1.toString}, ${_2.toString})"
}
*/