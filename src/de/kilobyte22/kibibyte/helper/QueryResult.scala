package de.kilobyte22.kibibyte.helper

import java.sql.ResultSet

class QueryResult(resultSet: ResultSet) {
  def reset = resultSet.absolute(1)
  def eachRow[T](cb: QueryRow => T) {
    reset
    val row = new QueryRow(resultSet)
    while (resultSet.next()) {
      cb(row)
    }
  }

  def get: Option[QueryRow] =
    if (resultSet.next())
      Some(new QueryRow(resultSet))
    else
      None
}
