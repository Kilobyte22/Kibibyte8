package de.kilobyte22.kibibyte.plugin

import org.yaml.snakeyaml.Yaml

import scala.collection.convert.WrapAsScala._

class PluginMetadata(dispname: String) {
  private val _dispname = dispname
}

object PluginMetadata {

  private val yaml = new Yaml

  def fromYamlData(data: Map[String, _]) = {
    val dispname = data("name")
  }

  def fromYaml(data: String) = fromYamlData(Map.empty[String, Unit])
}