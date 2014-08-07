package de.kilobyte22.kibibyte.helper

import java.sql.{PreparedStatement => PS}

class PreparedStatement(ps: PS) {

  private var fieldID = 1

  private def next(ignored: Unit) = {
    fieldID += 1
    this
  }

  def apply(value: String) = next(ps.setString(fieldID, value)) // <3 scala
  def apply(value: Int) = next(ps.setInt(fieldID, value))

  def run = new QueryResult(ps.executeQuery())
}

object PreparedStatement {
  implicit def execute(ps: PreparedStatement) =
    ps.run
}
