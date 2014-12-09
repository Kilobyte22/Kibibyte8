package de.kilobyte22.kibibyte.auth

class Account {
  def id: String = s"$service:$handler/$server/$name"

  def service: String = ???
  def handler: String = ???
  def server: String = ???
  def name: String = ???

  def identity = PermissionSystem.permissionHandler.identityFor(this)

}
