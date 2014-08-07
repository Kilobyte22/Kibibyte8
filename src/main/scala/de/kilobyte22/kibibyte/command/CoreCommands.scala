package de.kilobyte22.kibibyte.command

object CoreCommands {
  @command def echo(params: CommandParams) = params.sendChat(params.args)
}
