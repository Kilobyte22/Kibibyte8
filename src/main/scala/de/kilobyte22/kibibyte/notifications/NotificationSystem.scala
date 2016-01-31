package de.kilobyte22.kibibyte.notifications

import de.kilobyte22.kibibyte.api.auth.Identity

object NotificationSystem {
  def notify(identity: Identity, notification: Notification) = ???
  def notify(identity: Identity, notification: PushNotification) = ???
}
