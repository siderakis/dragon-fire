scalaVersion := "2.10.1"

organization := "siderakis"

name := "dragon-fire"

version := "0.1-SNAPSHOT"

libraryDependencies ++= Seq(
    "org.specs2" %% "specs2" % "2.2" % "test"
  )

  scalacOptions in Test ++= Seq("-Yrangepos")

  resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                    "releases"  at "http://oss.sonatype.org/content/repositories/releases")