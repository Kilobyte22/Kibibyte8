package de.kilobyte22.kibibyte.ipc

class IpcMessage(sender: Option[IpcMember])

case class IpcMessageCall(sender: Option[IpcMember], target: IpcMember, obj: IpcObject, method: IpcMethod, params: Seq[IpcValue], id: Int) extends IpcMessage(sender)

case class IpcMessageEvent(sender: IpcMember, event: IpcMethod, params: Seq[IpcValue]) extends IpcMessage(Some(sender))

case class IpcMessageCallResponse(sender: Option[IpcMember], id: Int, result: Either[IpcError, IpcValue]) extends IpcMessage(sender)