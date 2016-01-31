package de.kilobyte22.kibibyte.event

import de.kilobyte22.kibibyte.api.chat.User

trait UserEvent {
  val user: User
}
