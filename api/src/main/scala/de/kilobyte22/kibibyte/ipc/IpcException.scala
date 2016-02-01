package de.kilobyte22.kibibyte.ipc

class IpcException(code: Int, msg: String) extends Exception (s"Error $code: $msg")
