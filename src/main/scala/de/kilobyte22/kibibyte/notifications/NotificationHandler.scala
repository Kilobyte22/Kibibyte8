package de.kilobyte22.kibibyte.notifications

import de.kilobyte22.kibibyte.auth.Identity

trait NotificationHandler {
  def notify(identity: Identity, notification: Notification)
  def updateNotification(notification: Notification)
  def notificationsFor(identity: Identity): List[Notification]
}
