package de.kilobyte22.kibibyte.helper

import java.sql.ResultSet

class QueryRow(rs: ResultSet) {
  def string(key: String) = rs.getString(key)
  def string(id: Int) = rs.getString(id)
}
