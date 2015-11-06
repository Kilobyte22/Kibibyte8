name := "Kibibyte8"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.scalatest" % "scalatest_2.10.0" % "2.0.M5" % "test"

libraryDependencies += "de.kilobyte22.configparser" % "config-parser" % "1.0"
libraryDependencies += "de.kilobyte22.optionparser" % "optionparser-java" % "1.0"
libraryDependencies += "org.pircbotx" % "pircbotx" % "1.9"
libraryDependencies += "com.google.guava" % "guava" % "14.0.1"
libraryDependencies += "org.yaml" % "snakeyaml" % "1.15"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.7"

resolvers += "My Personal Repo" at "http://maven.kilobyte22.de"

mainClass in Compile := Some("de.kilobyte22.kibibyte.Main")