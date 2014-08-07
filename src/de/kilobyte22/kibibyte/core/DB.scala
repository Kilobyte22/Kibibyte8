package de.kilobyte22.kibibyte.core

import java.sql.Connection

import de.kilobyte22.kibibyte.helper.PreparedStatement

object DB {
  var database: Connection = null

  def prepare(query: String) = new PreparedStatement(database.prepareStatement(query))
}
