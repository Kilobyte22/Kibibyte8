package de.kilobyte22.kibibyte.ipc

import java.util.concurrent.atomic.AtomicInteger

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Channel


object IpcHandler {

  val handlers = ArrayBuffer.empty[(IpcMessage => Boolean, Boolean)]
  val eventHandlers = mutable.Map.empty[IpcMethod, ArrayBuffer[Seq[IpcValue] => _]]

  handlers += (({
    case e: IpcMessageEvent =>
      eventHandlers.get(e.event).map(_.map(_(e.params)))
      true
    case _ => false
  }, false))

  private val nextID = new AtomicInteger(0)

  def handleMessage(message: IpcMessage) = synchronized {
    handlers.foreach(handler => {
      if (handler._1(message) && handler._2)
        handlers -= handler
    })
  }

  def registerEventHandler[T](event: String)(handler: Seq[IpcValue] => T) = {
    eventHandlers.getOrElseUpdate(event, ArrayBuffer.empty[Seq[IpcValue] => _]) += handler
    handler
  }

  def unregisterEventHandler[T](event: String)(handler: Seq[IpcValue] => T) = {
    eventHandlers.get(event).map(_ -= handler)
  }

  def callThing(target: IpcMember, obj: IpcObject, method: IpcMethod, params: Seq[IpcValue]) = {
    val id = nextID.getAndIncrement()
    val channel = new Channel[IpcMessageCallResponse]

    handlers += (({
      case r: IpcMessageCallResponse if r.id == id =>
        channel.write(r)
        true
      case _ => false
    }, true))

    channel.read.result
  }
}
