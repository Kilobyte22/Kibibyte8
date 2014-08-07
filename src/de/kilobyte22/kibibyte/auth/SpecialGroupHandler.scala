package de.kilobyte22.kibibyte.auth

import de.kilobyte22.kibibyte.chat.{Chat, User}

trait SpecialGroupHandler {
  def groupsFor(user: User, chat: Chat): Iterable[String]
}
