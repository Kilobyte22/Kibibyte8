package de.kilobyte22.kibibyte.asm

trait Transformer {
  def transform(className: String, data: Array[Byte]): Array[Byte]
}
