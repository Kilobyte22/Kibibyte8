package de.kilobyte22.kibibyte.command

import de.kilobyte22.kibibyte.core.Kibibyte

object CoreCommands {
  @command def echo(params: CommandParams) = params.sendChat(params.args)
  @command def parse(params: CommandParams) =
    params.sendChat(CommandParser.run(params.args).toString)
  @command def run(params: CommandParams): Unit =
    params.sendChat(CommandParser.run(params.args).run(params.sender, params.chat, params.center, params.input))
  @command def poke(params: CommandParams) = params.sendChat(s"${params.args.mkString(": ")}: ${params.input}")
  @command def version(params: CommandParams) = params.sendChat(s"Kibibyte ${Kibibyte.Version} - Compiled on ${Kibibyte.CompileDate match {case Some(d) => d; case None => "Unknown"}}")
}
