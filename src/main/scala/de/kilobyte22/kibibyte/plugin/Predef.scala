package de.kilobyte22.kibibyte.plugin

import de.kilobyte22.kibibyte.message.FormattedString

object Predef {
  def format(s: String) = new FormattedString(s)
}
