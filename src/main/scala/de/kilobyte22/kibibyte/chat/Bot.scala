package de.kilobyte22.kibibyte.chat

import de.kilobyte22.kibibyte.auth.LoginHandler

trait Bot {
  def id: String
  def getUser(name: String): Option[User]
  def getChat(name: String): Option[Chat]

  private var _loginHandler: LoginHandler = null

  //region Getters/Setters
  def loginHandler = _loginHandler
  def loginHandler_=(value: LoginHandler) {
    if (_loginHandler != null)
      _loginHandler.disable(this)
    try {
      value.enable(this)
      _loginHandler = value
    } catch {
      case ex: Exception => ex.printStackTrace()
    }
  }
  //endregion

}
