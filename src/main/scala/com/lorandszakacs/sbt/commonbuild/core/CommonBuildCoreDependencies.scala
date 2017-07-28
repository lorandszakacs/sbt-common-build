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
  def scalacVersion: String = "2.12.3"

  private lazy val akkaVersion = "2.4.14"
  private lazy val akkaHttpVersion = "10.0.0"
  private lazy val catsVersion = "0.9.0"
  private lazy val shapelessVersion = "2.3.2"

  /**
    * All predefined dev dependencies should go in here.
    */
  object dev {

    object akka {
      def actor: ModuleID = "com.typesafe.akka" %% "akka-actor" % akkaVersion withSources()

      def http: ModuleID = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion withSources()
    }

    def cats: ModuleID = "org.typelevel" %% "cats" % catsVersion withSources()

    def shapeless: ModuleID =  "com.chuusai" %% "shapeless" % shapelessVersion withSources()

    def scalaParserCombinators: ModuleID = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6" withSources()

    def nScalaJodaTime: ModuleID = "com.github.nscala-time" %% "nscala-time" % "2.16.0" withSources()

    def reactiveMongo: ModuleID = "org.reactivemongo" %% "reactivemongo" % "0.12.4" withSources()

    def typeSafeConfig: ModuleID = "com.typesafe" % "config" % "1.3.1" withSources()

    //these two dependencies have to be put together on the ClassPath
    def scalaLogging: ModuleID = "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0" withSources()

    def logbackClassic: ModuleID = "ch.qos.logback" % "logback-classic" % "1.1.7" withSources()

    def pprint: ModuleID = "com.lihaoyi" %% "pprint" % "0.4.3" withSources()

    object java {
      def jsoup: ModuleID = "org.jsoup" % "jsoup" % "1.8.1" withSources()
    }

    def resolvers: Seq[MavenRepository] = Seq(
      "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
      "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
    )

  }

  /**
    * All predefined test dependencies should go in here.
    */
  object test {
    def scalaTest: ModuleID = "org.scalatest" %% "scalatest" % "3.0.1" % Test withSources()

    def scalaCheck: ModuleID = "org.scalacheck" %% "scalacheck" % "1.12.2" % Test withSources()

    def scalaMock: ModuleID = "org.scalamock" %% "scalamock-scalatest-support" % "3.5" % Test withSources()

    object akka {
      def testkit: ModuleID = "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test withSources()

      def httpTestkit: ModuleID = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test withSources()
    }


    def resolvers: Seq[MavenRepository] = Seq()
  }

}
