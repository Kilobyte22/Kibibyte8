package de.kilobyte22.kibibyte.api.auth

trait Group {
  /**
   * Checks if this Group has a certain permission
   * @param permission The Permission to check for
   * @return `Some(value) if this group explicitely has a permission set, otherwise `None`
   */
  def set(permission: String, value: Option[Boolean])
  def hasPermission(permission: String): Option[Boolean]
  def +=(user: Identity)
  def -=(user: Identity)
}
