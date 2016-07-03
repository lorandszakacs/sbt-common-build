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
package com.lorandszakacs.sbt.commonbuild.core

import sbt._
import Keys._

/**
 * @author Lorand Szakacs, lsz@lorandszakacs.com
 * @since 16 Mar 2015
 *
 *        Note that all members that have type Seq[sbt.ModuleID]
 *        are appended to the `libraryDependencies` sbt key.
 *        this reduces the boilerplate of having to explicitly
 *        specify what goes in as a libraryDependency
 *
 *        You can find a sample build in this package
 */
private[commonbuild] trait CommonBuildCore extends Build with CommonBuildCoreDependencies {

  def orgPath: String = "com.lorandszakacs"

  def orgHomepage: Option[URL] = Some(url("http://lorandszakacs.com/"))

  def orgName: String = "Lorand Szakacs"

  def scalacVersion: String = "2.12.0-M4"

  def projectVersion: String

  final def commonBuildSettings: Seq[Setting[_]] =
    Defaults.coreDefaultSettings ++
      commonSettings ++
      pluginSettings ++
      Seq(
        libraryDependencies ++= commonBuildLibraryDependencies,
        resolvers ++= dev.resolvers ++ test.resolvers
      ) ++
      otherSettings

  private final def commonBuildLibraryDependencies: Seq[ModuleID] =
    devDependencies ++
      runtimeDependencies ++
      testDependencies ++
      otherDependencies

  /**
   * maven-like artifacts for production dependencies
   * should go here. Eliminates the need of explicitly
   * specifying the dependencyKey.
   */
  def devDependencies: Seq[ModuleID]

  def testDependencies: Seq[ModuleID]

  def runtimeDependencies: Seq[ModuleID]

  def otherDependencies: Seq[ModuleID]

  /**
   * Most likely should never be provided explicitly,
   * rather, mix in some of the plugin traits, the traits
   * should do an abstract override to get rid of this thing.
   */
  def pluginSettings: Seq[Setting[_]]

  /**
   * Since these settings are appended in last you can use it
   * to override any of the previous settings, although such
   * behavior is strongly discouraged and you should probably
   * rethink the build if it comes to that. 
   */
  def otherSettings: Seq[Setting[_]]

  def commonSettings: Seq[Setting[_]] =
    scalaCompilerSettings ++
      Seq(
        organization := orgPath,
        organizationHomepage := orgHomepage,
        organizationName := orgName,
        version := projectVersion,
        resolvers ++= resolverSettings
      )

  def scalaCompilerSettings: Seq[Setting[_]] =
    Seq(
      scalaVersion := scalacVersion,
      scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-encoding", "utf8")
    )

  def resolverSettings: Seq[MavenRepository] = Seq(
    "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  )

  object publishing {
    lazy val typesafeResolver: MavenRepository = "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
    lazy val apacheLicense = "The Apache Software Licence, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")

    lazy val lorandszakacs: Developer = Developer(
      id = "lorandszakacs",
      name = "Lorand Szakacs",
      url = "https://github.com/lorandszakacs"
    )
  }

  def publishingInfo: Option[PublishInfo]

  def publishingSettings: Seq[Setting[_]] = {
    if (publishingInfo.isDefined) {
      val pi = publishingInfo.get
      Seq(
        resolvers += pi.resolver,
        licenses := Seq(pi.license),
        pomIncludeRepository := { _ => false},
        publishMavenStyle := true,
        publishArtifact in Test := false,
        publishMavenStyle := false,
        exportJars := true,
        homepage := Some(url(pi.homepage)),
        publishTo <<= version { (v: String) =>
          val nexus = "https://oss.sonatype.org/"
          if (v.trim.endsWith("SNAPSHOT"))
            Some("snapshots" at nexus + "content/repositories/snapshots")
          else
            Some("releases" at nexus + "service/local/staging/deploy/maven2")
        },
        pomExtra :=
          <url>
            {pi.homepage}
          </url>
            <licenses>
              <license>
                <name>
                  {pi.license._1}
                </name>
                <url>
                  {pi.license._2.toString}
                </url>
                <distribution>repo</distribution>
              </license>
            </licenses>
            <scm>
              <connection>
                {pi.scm.connection}
              </connection>
              <url>
                {pi.scm.url}
              </url>
            </scm>
            <developers>
              <developer>
                <id>
                  {pi.developer.id}
                </id>
                <name>
                  {pi.developer.name}
                </name>
                <url>
                  {pi.developer.url}
                </url>
              </developer>
            </developers>
      )
    } else Nil
  }
}

case class PublishInfo(
  resolver: MavenRepository,
  license: (String, URL),
  homepage: String,
  scm: Scm,
  developer: Developer
  )


case class Scm(
  url: String,
  connection: String
  )

case class Developer(
  id: String,
  name: String,
  url: String
  )