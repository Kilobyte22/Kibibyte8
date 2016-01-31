package de.kilobyte22.kibibyte.api.auth

import de.kilobyte22.kibibyte.api.chat.{Chat, User}

trait SpecialGroupHandler {
  def groupsFor(user: User, chat: Chat): Iterable[String]
}
