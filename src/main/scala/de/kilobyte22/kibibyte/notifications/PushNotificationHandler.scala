package de.kilobyte22.kibibyte.notifications

import de.kilobyte22.kibibyte.auth.Identity

trait PushNotificationHandler {
  def notify(identity: Identity, notification: PushNotification)
}
