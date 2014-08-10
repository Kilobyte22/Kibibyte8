package de.kilobyte22.kibibyte.notifications

trait NotificationHandler {
  def notify(notification: Notification)
  def notify(notification: PushNotification)
  def updateNotification(notification: Notification)
}
