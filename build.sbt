name := "rock-paper-scissors"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.4" % "test"

libraryDependencies += "org.mockito" % "mockito-all" % "1.9.5" % "test"

scalacOptions += "-Xfatal-warnings" // in this case it is activated so that pattern matches which are not exhaustive fail badly