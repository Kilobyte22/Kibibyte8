package de.kilobyte22.kibibyte.auth

import de.kilobyte22.kibibyte.chat.User

trait Group {
  /**
   * Checks if this Group has a certain permission
   * @param permission The Permission to check for
   * @return `Some(value) if this group explicitely has a permission set, otherwise `None`
   */
  def hasPermission(permission: String): Option[Boolean]
  def set(permission: String, value: Option[Boolean])
  def +=(user: User)
  def -=(user: User)
}
