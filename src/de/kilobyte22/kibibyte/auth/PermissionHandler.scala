package de.kilobyte22.kibibyte.auth

import de.kilobyte22.kibibyte.chat.User

trait PermissionHandler {
  /**
   * Checks permission of a user on a specific node
   * @param user The user to check. Will be ''null'' for the default user
   * @param permission The permission node to check for
   * @return `Some(value)` if permission is explicit set, otherwise `None`
   */
  def hasPermission(user: User, permission: String): Option[Boolean]

  /**
   * Returns all groups a user is member of
   * @param user The user
   * @return The groups
   */
  def groupsFor(user: User): Iterable[Group]

  /**
   * Sets a permission for a user
   * @param user The user
   * @param permission The permission node
   * @param value Some(value) to explicitely grant/deny that permission, None to reset
   */
  def set(user: User, permission: String, value: Option[Boolean])

  /**
   * Get a group by its name, possibly creating it
   * @param name The name of the group
   * @return The group
   */
  def getGroup(name: String): Group

  /**
   * Called When the [[PermissionHandler]] gets picked. Set up your stuff here
   */
  def onRegister()

  /**
   * Called After the [[PermissionHandler]] gets replaced. Use for cleanup
   */
  def onUnregister()
}
