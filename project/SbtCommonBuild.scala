/**
 * Copyright 2015 Lorand Szakacs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
import sbt._
import sbt.Keys._

object SbtCommonBuild extends Build {

  lazy val root = Project(
    "sbt-common-build",
    file("."),
    aggregate = Seq(sbtCommonBuildCore, sbtCommonBuildPlugin),
    settings = commonSettings ++ Seq(
      publishArtifact := false
    )
  )

  lazy val sbtCommonBuildCore = Project(
    "sbt-common-build-core",
    file("sbt-common-build-core"),
    settings = commonSettings ++ Seq(
      libraryDependencies ++= Seq()
    )
  )

  lazy val sbtCommonBuildPlugin = Project(
    "sbt-common-build-plugin",
    file("sbt-common-build-plugin"),
    dependencies = Seq(sbtCommonBuildCore),
    settings = commonSettings
  )

  def commonSettings =
    Defaults.coreDefaultSettings ++
      Seq(
        organization := "com.lorandszakacs",
        // version is defined in version.sbt in order to support sbt-release
        scalacOptions ++= Seq("-unchecked", "-deprecation"),
        publishTo := {
          val id = if (isSnapshot.value) "snapshots" else "releases"
          val uri = "https://private-repo.typesafe.com/typesafe/ivy-" + id
          Some(Resolver.url("typesafe-" + id, url(uri))(Resolver.ivyStylePatterns))
        },
        sbtPlugin := true,
        publishMavenStyle := false,
        sbtVersion in GlobalScope := {
          System.getProperty("sbt.build.version", (sbtVersion in GlobalScope).value)
        },
        scalaVersion := {
          (sbtVersion in GlobalScope).value match {
            case sbt013 if sbt013.startsWith("0.13.") => "2.10.4"
            case sbt012 if sbt012.startsWith("0.12.") => "2.9.3"
            case _ => "2.9.3"
          }
        },
        sbtDependency in GlobalScope := {
          (sbtDependency in GlobalScope).value.copy(revision = (sbtVersion in GlobalScope).value)
        },
        publishArtifact in(Compile, packageDoc) := false,
        publishArtifact in(Compile, packageSrc) := false
      ) ++ publishSettings

  def publishSettings = Seq(
    resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    licenses := Seq("The Apache Software Licence, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    homepage := Some(url("https://github.com/lorandszakacs/sbt-common-build")),
    pomIncludeRepository := { _ => false},
    publishMavenStyle := false,
    publishArtifact in Test := false,
    publishMavenStyle := false,
    exportJars := true,
    pomExtra := {
      <url>https://github.com/lorandszakacs/sbt-common-build</url>
        <licenses>
          <license>
            <name>Apache</name>
            <url>http://www.apache.org/licenses/LICENSE-2.0</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <connection>scm:git@github.com:lorandszakacs/sbt-common-build.git</connection>
          <url>https://github.com/lorandszakacs/sbt-common-build</url>
        </scm>
        <developers>
          <developer>
            <id>lorandszakacs</id>
            <name>Lorand Szakacs</name>
            <url>https://github.com/lorandszakacs</url>
          </developer>
        </developers>
    },

    publishTo <<= version { (v: String) =>
      val nexus = "https://oss.sonatype.org/"
      if (v.trim.endsWith("SNAPSHOT"))
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases" at nexus + "service/local/staging/deploy/maven2")
    }
  )
}

