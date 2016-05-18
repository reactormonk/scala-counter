lazy val counter = crossProject.crossType(CrossType.Pure).in(file("."))
  .settings(
    name := "counter",
    organization := "org.reactormonk",
    libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.5" % Test,
    scalaVersion := "2.11.8",
    version := "1.3.3",
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
    </developers>
  )
)

publishArtifact in Test := false

lazy val jvm = counter.jvm
lazy val js = counter.js
