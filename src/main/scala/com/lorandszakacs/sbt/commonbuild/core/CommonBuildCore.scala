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
  */
object CommonBuildCore extends CommonBuildCoreDependencies {

  final def buildSettings(project: ProjectInfo, org: OrganizationInfo, publishingInfo: Option[PublishInfo] = None): Seq[Setting[_]] =
    Defaults.coreDefaultSettings ++
      commonSettings(project, org, publishingInfo) ++
      Seq(
        resolvers ++= dev.resolvers ++ test.resolvers
      )

  private def commonSettings(project: ProjectInfo, org: OrganizationInfo, publishingInfo: Option[PublishInfo] = None): Seq[Setting[_]] =
    scalaCompilerSettings ++
      Seq(
        organization := org.organization,
        organizationHomepage := org.homepage,
        organizationName := org.name,
        version := project.version,
        resolvers ++= resolverSettings
      ) ++ {
      if (publishingInfo.isEmpty) {
        Nil
      } else {
        publishingSettings(publishingInfo.get)
      }
    }

  private def scalaCompilerSettings: Seq[Setting[_]] =
    Seq(
      scalaVersion := scalacVersion,
      scalacOptions ++= Seq(
        "-language:existentials",
        "-language:higherKinds",
        "-language:implicitConversions",
        "-encoding", "UTF-8",
        "-deprecation",
        "-unchecked",
        "-feature",
        "-Yno-adapted-args",
        "-Ywarn-dead-code",
        "-Xfuture",
        "-Xlint"
      )
    )

  private def resolverSettings: Seq[MavenRepository] = Seq(
    "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  )

  object defaults {
    lazy val lorandszakacs: Developer = Developer(
      id = "lorandszakacs",
      name = "Lorand Szakacs",
      email = "lsz@lorandszakacs.com",
      url = url("https://github.com/lorandszakacs")
    )

    lazy val lorandszakacsOrg = OrganizationInfo(
      organization = "com.lorandszakacs",
      homepage = Option(url("https://github.com/lorandszakacs")),
      name = "Lorand Szakacs"
    )

    def lorandszakacsPublishingInfo(scm: ScmInfo) = PublishInfo(
      resolver = publishing.typesafeResolver,
      license = publishing.apacheLicense,
      homepage = lorandszakacsOrg.homepage.get.toExternalForm,
      scm = scm,
      developer = lorandszakacs
    )
  }

  object publishing {
    lazy val typesafeResolver: MavenRepository = "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
    lazy val apacheLicense: (String, URL) = "The Apache Software Licence, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
  }

  def publishingSettings(pi: PublishInfo): Seq[Setting[_]] = {
    Seq(
      resolvers += pi.resolver,
      licenses := Seq(pi.license),
      pomIncludeRepository := { _ => false },
      publishMavenStyle := true,
      publishArtifact in Test := false,
      publishMavenStyle := false,
      exportJars := true,
      homepage := Some(url(pi.homepage)),
      publishTo := {
        val nexus = "https://oss.sonatype.org/"
        if (version.value.trim.endsWith("SNAPSHOT"))
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
              {pi.scm.browseUrl.toExternalForm}
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
  }
}

case class PublishInfo(
  resolver: MavenRepository,
  license: (String, URL),
  homepage: String,
  scm: ScmInfo,
  developer: Developer
)

case class OrganizationInfo(
  organization: String,
  homepage: Option[URL],
  name: String
)

case class ProjectInfo(
  version: String
)