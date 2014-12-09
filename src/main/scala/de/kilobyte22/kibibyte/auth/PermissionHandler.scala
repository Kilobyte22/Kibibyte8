package de.kilobyte22.kibibyte.auth

trait PermissionHandler {
  /**
   * Checks permission of a user on a specific node
   * @param user The user to check. Will be ''null'' for the default user
   * @param permission The permission node to check for
   * @return `Some(value)` if permission is explicit set, otherwise `None`
   */
  def hasPermission(user: Identity, permission: String): Option[Boolean]

  /**
   * Returns all groups a user is member of
   * @param user The user
   * @return The groups
   */
  def groupsFor(user: Identity): Iterable[Group]

  /**
   * Sets a permission for a user
   * @param user The user
   * @param permission The permission node
   * @param value Some(value) to explicitely grant/deny that permission, None to reset
   */
  def set(user: Identity, permission: String, value: Option[Boolean])

  /**
   * Get a group by its name, possibly creating it
   * @param name The name of the group
   * @return The group
   */
  def getGroup(name: String): Group

  /**
   * Get the identity for a user. Always returns an Identity. If there is none stored, this will be an implicit one
   * @param account The account to get the identity for.
   * @return
   */
  def identityFor(account: Account): Identity

  /**
   * Adds an account to an existing identity
   * @param account The account to add
   * @param identity
   */
  def addAccountToIdentity(account: Account, identity: Identity)

  /**
   * Removes an account from an identity. If the resulting identity has no further connected accounts it should be automatically deleted
   * @param account The account to remove from its identity
   */
  def removeAccountFromIdentity(account: Account)

  def getOrCreateIdentity(name: String): Identity

  /**
   * Called When the [[PermissionHandler]] gets picked. Set up your stuff here
   */
  def onRegister()

  /**
   * Called After the [[PermissionHandler]] gets replaced. Use for cleanup
   */
  def onUnregister()
}