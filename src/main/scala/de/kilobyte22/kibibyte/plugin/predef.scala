package de.kilobyte22.kibibyte.plugin

import de.kilobyte22.kibibyte.message.FormattedString

object predef {
  def format(s: String) = new FormattedString(s)
}
