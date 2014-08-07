package de.kilobyte22.kibibyte.asm

class TransformingClassLoader extends ClassLoader {
  override def findClass(name: String) = {
    val data = fromClasspath(name)
    val tdata = transform(name, data)
    defineClass(name, tdata, 0, tdata.length)
  }

  def fromClasspath(name: String) = {
    Array[Byte]()
  }

  def transform(name: String, data: Array[Byte]) = {
    data
  }
}
