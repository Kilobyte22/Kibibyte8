package de.kilobyte22.kibibyte.api.notification

import de.kilobyte22.kibibyte.api.auth.Identity
import de.kilobyte22.kibibyte.notifications.PushNotification

trait PushNotificationHandler {
  def notify(identity: Identity, notification: PushNotification)
}
