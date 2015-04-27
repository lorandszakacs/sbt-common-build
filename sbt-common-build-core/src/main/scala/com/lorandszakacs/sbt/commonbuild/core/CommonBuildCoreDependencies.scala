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

/**
 * @author Lorand Szakacs, lsz@lorandszakacs.com
 * @since 16 Mar 2015
 *
 *        Dependencies that are shared by at least two projects should
 *        go in here. This reduces the risk of using different versions
 *        of the same library between all CareConnect projects.
 */
private[core] trait CommonBuildCoreDependencies {

  /**
   * All predefined dev dependencies should go in here.
   */
  object dev {
    final lazy val scalaParserCombinators = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.2" withSources()

    final lazy val lorandszakacsUtilHtml = "com.lorandszakacs" %% "util-html" % "0.1.1" withSources()
    final lazy val lorandszakacsUtilIO = "com.lorandszakacs" %% "util-io" % "0.1.0" withSources()

    final lazy val nScalaJodaTime = "com.github.nscala-time" %% "nscala-time" % "1.8.0"

    final lazy val akkaActor = "com.typesafe.akka" %% "akka-actor" % "2.3.8" withSources()
    final lazy val akkaslf4j = "com.typesafe.akka" %% "akka-slf4j" % "2.3.8" withSources()
    final lazy val akkaContrib = "com.typesafe.akka" %% "akka-contrib" % "2.3.8" withSources()
    final lazy val akkaStreamExperimental = "com.typesafe.akka" % "akka-stream-experimental_2.11" % "1.0-M4" withSources()
    final lazy val akkaHttpExperimental = "com.typesafe.akka" %% "akka-http-experimental" % "1.0-M4" withSources()
    final lazy val akkaHttpCoreExperimental = "com.typesafe.akka" %% "akka-http-core-experimental" % "1.0-M4" withSources()

    final lazy val akka = Seq(akkaActor, akkaslf4j, akkaContrib)

    final lazy val reactiveMongo = "org.reactivemongo" %% "reactivemongo" % "0.10.5.0.akka23" withSources()

    final lazy val jsoup = "org.jsoup" % "jsoup" % "1.8.1" withSources()

    final lazy val typeSafeConfig = "com.typesafe" % "config" % "1.2.1" withSources()

    //spray json evolves independently from the other spray components. Hence different version numbers.
    final lazy val sprayJson = "io.spray" %% "spray-json" % "1.3.1" withSources()

    final lazy val sprayCan = "io.spray" %% "spray-can" % "1.3.2" withSources()
    final lazy val sprayHttp = "io.spray" %% "spray-http" % "1.3.2" withSources()
    final lazy val sprayHttpx = "io.spray" %% "spray-httpx" % "1.3.2" withSources()
    final lazy val sprayClient = "io.spray" %% "spray-client" % "1.3.2" withSources()
    final lazy val sprayUtil = "io.spray" %% "spray-util" % "1.3.2" withSources()
    final lazy val sprayServlet = "io.spray" %% "spray-servlet" % "1.3.2" withSources()
    final lazy val sprayRouting = "io.spray" %% "spray-routing" % "1.3.2" withSources()
    final lazy val sprayCaching = "io.spray" %% "spray-caching" % "1.3.2" withSources()

    //these two dependencies have to be put together on the ClassPath
    final lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0" withSources()
    final lazy val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.1.2" withSources()

    def resolvers: Seq[MavenRepository] = Seq(
      "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
      "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",

      "spray repo" at "http://repo.spray.io",
      "spray nightlies repo" at "http://nightlies.spray.io")
  }

  /**
   * All predefined test dependencies should go in here.
   */
  object test {
    final lazy val scalaTest = "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test" withSources()
    final lazy val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.12.2" % "test" withSources()
    final lazy val scalaMock = "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test" withSources()
    final lazy val sprayTestKit = "io.spray" %% "spray-testkit" % "1.3.2" % "test" withSources()

    def resolvers: Seq[MavenRepository] = Seq()
  }

}