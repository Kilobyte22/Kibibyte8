package de.kilobyte22.kibibyte.plugin

import java.net.{URL, URLClassLoader}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.ref.WeakReference

class PluginClassLoader(group: PluginClassLoaderGroup, urls: Array[URL], parent: ClassLoader = ClassLoader.getSystemClassLoader) extends URLClassLoader(urls, parent) {
  group.iAmNew(this)
}

class PluginClassLoaderGroup {
  val loaders = mutable.WeakHashMap.empty[PluginClassLoader, Unit]
  protected[plugin] def iAmNew(loader: PluginClassLoader) = {
    loaders(loader) = Unit // We only need the keys, but this seems the easiest way to do it...
  }

  def loadClassDelegated(me: PluginClassLoader, clazz: String): Class[_] = {
    loaders.keys.filter(_ != me).foreach (loader => {
      try {
        return Class.forName(clazz, true, loader)
      } catch {
        case e: ClassNotFoundException => Unit // Ignore the exception
        case e: Throwable => throw e // rethrow
      }
    })
    null
  }
}
