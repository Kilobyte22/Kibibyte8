package de.kilobyte22.kibibyte.message

import java.awt.Color

class MessagePart(var text: String) {
  var color = Color.BLACK
  var backgroundColor = Color.WHITE
  var bold, italic, underline = false
}
