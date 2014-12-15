package de.kilobyte22.kibibyte.auth

import de.kilobyte22.kibibyte.notifications.{Notification, NotificationSystem, PushNotification}

trait Identity {

  def name: String

  def accounts: List[Account]
  def checkPassword(password: String)

  val isExplicit: Boolean

  def hasPermission(perm: String): Boolean

  def makeExplicit(name: String)

  def notify(notification: Notification) = NotificationSystem.notify(this, notification)
  def notify(notification: PushNotification) = NotificationSystem.notify(this, notification)
}
