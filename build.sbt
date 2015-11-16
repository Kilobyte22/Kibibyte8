name := "Kibibyte8"

version := "1.0"

scalaVersion := "2.11.7"

lazy val deployDirectory = settingKey[File]("Where to deploy Kibibyte")
deployDirectory := baseDirectory.value / "deploy"

libraryDependencies += "org.scalatest" % "scalatest_2.10.0" % "2.0.M5" % "test"

libraryDependencies += "de.kilobyte22.configparser" % "config-parser" % "1.0"
libraryDependencies += "de.kilobyte22.optionparser" % "optionparser-java" % "1.0"
libraryDependencies += "org.pircbotx" % "pircbotx" % "1.9"
libraryDependencies += "com.google.guava" % "guava" % "14.0.1"
libraryDependencies += "org.yaml" % "snakeyaml" % "1.15"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.7"

resolvers += "My Personal Repo" at "http://maven.kilobyte22.de"

mainClass in Compile := Some("de.kilobyte22.kibibyte.Main")
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

lazy val getDeps = taskKey[Unit]("Outputs dependencies")
getDeps := println(libraryDependencies.value.toString)

lazy val deploy = taskKey[Unit]("Deploys kibibyte")
deploy := {
	val log = streams.value.log
	val dir = deployDirectory.value
	dir.mkdir()
	log.info("Installing jar file")
	val binary = (Keys.`package` in Compile).value
	IO.copy(Seq(binary -> dir / "kibibyte.jar"))

	log.info("Installing dependencies")

	val home = new File(System.getProperty("user.home"))

	(dir / "libs").mkdir()
	(dir / "libs").listFiles.foreach(_.delete())

	val libMap = libraryDependencies.value.flatMap (lib => {
		val libParts = lib.toString.split(':')
		val dirs = Seq(
			home / ".ivy2" / "cache" / libParts(0) / libParts(1) / "jars" / s"${libParts(1)}-${libParts(2)}.jar",
			home / ".ivy2" / "cache" / libParts(0) / libParts(1) / "bundles" / s"${libParts(1)}-${libParts(2)}.jar"
		)
		dirs.flatMap(l => if (l.exists())
			Seq((l, dir / "libs" / s"${libParts(1)}-${libParts(2)}.jar"))
		else
			Seq.empty[(File, File)]
		)
	})
	IO.copy(libMap)

	if (!(dir / "kibibyte.cfg").exists())
		IO.copy(Seq((baseDirectory.value / "src" / "main" / "resources" / "example.cfg") -> (dir / "kibibyte.cfg")))
	
}

