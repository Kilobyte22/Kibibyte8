import sbt._
import Keys._

object KibibyteBuild extends Build {

  lazy val root = Project(id = "kibibyte_core", base = file("core")) dependsOn(api)

  lazy val api = Project(id = "kibibyte_api", base = file("api"))
  lazy val jarloader = Project(id = "kibibyte_jarloader", base = file("jarloader"))

}
