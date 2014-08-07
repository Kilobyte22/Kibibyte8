package de.kilobyte22.kibibyte.command

import java.lang.reflect.Method

class CommandInfo(o: AnyRef, m: Method, val name: String, val namespace: String) {
  def run(params: CommandParams) {
    m.invoke(o, params)
  }
}
