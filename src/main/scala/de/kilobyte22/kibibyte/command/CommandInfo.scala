package de.kilobyte22.kibibyte.command

import java.lang.invoke.MethodHandle
import java.lang.reflect.Method

class CommandInfo(o: AnyRef, m: MethodHandle, val name: String, val namespace: String) {
  def run(params: CommandParams) {
    m.invoke(o, params)
  }
}
