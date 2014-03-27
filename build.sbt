name := "Counter"

organization := "org.reactormonk"

version := "1.3.0"

scalaVersion := "2.10.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomExtra := (
  <url>http://github.com/Tass/scala-counter</url>
  <licenses>
    <license>
      <name>Beerware</name>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:Tass/scala-counter.git</url>
    <connection>scm:git:git@github.com:Tass/scala-counter.git</connection>
  </scm>
  <developers>
    <developer>
      <id>reactormonk</id>
      <name>Simon Hafner</name>
      <url>http://reactormonk.org</url>
    </developer>
  </developers>)
