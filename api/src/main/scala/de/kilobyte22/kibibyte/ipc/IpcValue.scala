package de.kilobyte22.kibibyte.ipc

class IpcValue {

}

case class IpcString(value: String) extends IpcValue
case class IpcInt(value: Int) extends IpcValue
case class IpcDouble(value: Double) extends IpcValue
case class IpcBool(value: Boolean) extends IpcValue
case class IpcSeq(values: Seq[IpcValue]) extends IpcValue
case class IpcStruct(values: Seq[IpcValue]) extends IpcValue
case class IpcMap(values: Map[String, IpcValue]) extends IpcValue
case object IpcNil extends IpcValue