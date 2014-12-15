package de.kilobyte22.kibibyte.notifications

import java.util.UUID

class Notification(_title: String, _body: String = null, _url: String = null) {
  // TODO: Database interaction

  private var _id: UUID = null
  protected[notifications] def this(id: UUID, title: String, body: String, url: String) {
    this(title, body, url)
    _id = id
  }
  lazy val id: UUID = _id
  def url: String = _url
  def body: String = _url
  def title: String = _url

  def title_=(value: String) = ???
  def body_=(value: String) = ???
  def url_=(value: String) = ???

  private def updateHelper[T](arg: T): T = {

    arg
  }
}

