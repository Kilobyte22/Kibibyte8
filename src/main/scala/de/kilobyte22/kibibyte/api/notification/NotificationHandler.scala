package de.kilobyte22.kibibyte.api.notification

import de.kilobyte22.kibibyte.api.auth.Identity
import de.kilobyte22.kibibyte.notifications.Notification

trait NotificationHandler {
  def notify(identity: Identity, notification: Notification)
  def updateNotification(notification: Notification)
  def notificationsFor(identity: Identity): List[Notification]
}
