/*package de.kilobyte22.kibibyte.command

import scala.collection.mutable
import scala.util.parsing.input.{Position, Reader}

object CommandTokenizer {
  def tokenize(input: String) = {
    val i = input.replace("\r\n", "\n").replace("\r", "\n")
    val lines = i.split("\n")
    val tokens = mutable.ArrayBuffer.empty[Token]

    var pos = 0
    var col = 0
    var line = 1
    var lineCont = lines(0)
    var escaped = false
    var tmp = ""

    def next() = {
      val ret = i.charAt(pos)
      pos += 1
      col += 1
      if (ret == '\n') {
        line += 1
        lineCont = lines(line - 1)
        col = 0
      }
      ret
    }
    def hasNext = {
      pos < i.length
    }
    def endToken() = {
      tokens += (tmp.trim match {
        case "|" => TokenPipe(line, col, lineCont)
        case s: String => TokenCommand(line, col, lineCont, s)
      })
      tmp = ""
    }

    while (hasNext)
      if (escaped) next() match {
        case 'n' => tmp += '\n'
        case c: Char => tmp += c
      } else next() match {
        case '|' =>
          endToken()
          tmp = "|"
          endToken()
      }
    tokens.toArray
  }
}

sealed class MyReader(tokens : Iterable[Token]) extends Reader[Token] {
  def pos : Position = tokens.head
  def atEnd : Boolean = tokens.isEmpty
  def rest : Reader[Token] = new MyReader(tokens.tail)
  def first : Token = tokens.head
}

sealed abstract class Token extends Position {
  val _line: Int
  val _col: Int
  val _lineCont: String

  override def column = _col
  override def line = _line
  override def lineContents = _lineCont
}

case class TokenCommand(_line: Int, _col: Int, _lineCont: String, data: String) extends Token
case class TokenPipe(_line: Int, _col: Int, _lineCont: String) extends Token*/