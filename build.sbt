name := "Counter"

organization := "org.reactormonk"

version := "1.3.3"

crossScalaVersions := Seq("2.10.3", "2.11.7")

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.5" % "test"

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
  <url>http://github.com/reactormonk/scala-counter</url>
  <licenses>
    <license>
      <name>Beerware</name>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:reactormonk/scala-counter.git</url>
    <connection>scm:git:git@github.com:reactormonk/scala-counter.git</connection>
  </scm>
  <developers>
    <developer>
      <id>reactormonk</id>
      <name>Simon Hafner</name>
      <url>http://reactormonk.org</url>
    </developer>
  </developers>)
