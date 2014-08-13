package de.kilobyte22.kibibyte.command

object CoreCommands {
  @command def echo(params: CommandParams) = params.sendChat(params.args)
  @command def parse(params: CommandParams) =
    params.sendChat(CommandParser.run(params.args).toString)
  @command def poke(params: CommandParams) = params.sendChat(s"${params.args(0)}: ${params.input}")
}
