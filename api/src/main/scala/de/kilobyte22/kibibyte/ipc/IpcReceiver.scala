package de.kilobyte22.kibibyte.ipc

trait IpcReceiver {
  /**
    * Call an IPC method on this object
    * @param method The method to call
    * @param params The params for the called method
    * @throws de.kilobyte22.kibibyte.ipc.IpcException If something bad happens in the IPC stack
    * @return Left(errorCode, errorDescription) if failed, otherwise the return value
    */
  @throws[IpcException]("Internal IPC Error")
  def callMethod(method: IpcMethod, params: Seq[IpcValue]): Either[IpcError, IpcValue] = {
    Left((0, ""))
  }
  def raiseEvent()
  val ipcReceiverID: IpcMember
}
