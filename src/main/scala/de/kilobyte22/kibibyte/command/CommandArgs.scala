package de.kilobyte22.kibibyte.command

class CommandArgs(val data: Array[String]) extends Iterable[String] {
  override def iterator: Iterator[String] = new IntIterator
  def apply(id: Int) = data(id)

  def join(start: Int = 0, end: Int = data.length) = data.slice(start, end).mkString(" ")
  override def mkString(sep: String) = data.mkString(sep)
  override def slice(start: Int, end: Int) = data.slice(start, end)


  class IntIterator extends Iterator[String] {
    var id = -1
    override def hasNext: Boolean = data.length > id + 1

    override def next(): String = {id += 1; data(id)}
  }
}

object CommandArgs {
  implicit def toObject(in: CommandArgs): Array[String] = in.data
  implicit def toString(in: CommandArgs): String = in.join()
  def apply(args: Array[String]) = new CommandArgs(args)
}
