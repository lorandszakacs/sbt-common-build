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
    final def scalaParserCombinators = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4" withSources()

    final def lorandszakacsUtilHtml = "com.lorandszakacs" %% "util-html" % "0.1.1" withSources()

    final def lorandszakacsUtilIO = "com.lorandszakacs" %% "util-io" % "0.1.0" withSources()

    final def nScalaJodaTime = "com.github.nscala-time" %% "nscala-time" % "2.12.0"

    final def akkaActor = "com.typesafe.akka" %% "akka-agent" % "2.4.7"

    final def akkaCamel = "com.typesafe.akka" %% "akka-camel" % "2.4.7"

    final def akkaCluster = "com.typesafe.akka" %% "akka-cluster" % "2.4.7"

    final def akkaClusterSMetrics = "com.typesafe.akka" %% "akka-cluster-metrics" % "2.4.7"

    final def akkaClusterSharding = "com.typesafe.akka" %% "akka-cluster-sharding" % "2.4.7"

    final def akkaClusterTools = "com.typesafe.akka" %% "akka-cluster-tools" % "2.4.7"

    final def akkaContrib = "com.typesafe.akka" %% "akka-contrib" % "2.4.7"

    final def akkaMultiNodeTestkit = "com.typesafe.akka" %% "akka-multi-node-testkit" % "2.4.7"

    final def akkaOSGI = "com.typesafe.akka" %% "akka-osgi" % "2.4.7"

    final def akkaPersistence = "com.typesafe.akka" %% "akka-persistence" % "2.4.7"

    final def akkaPersistenceTCK = "com.typesafe.akka" %% "akka-persistence-tck" % "2.4.7"

    final def akkaRemote = "com.typesafe.akka" %% "akka-remote" % "2.4.7"

    final def akkaSLF4J = "com.typesafe.akka" %% "akka-slf4j" % "2.4.7"

    final def akkaStream = "com.typesafe.akka" %% "akka-stream" % "2.4.7"

    final def akkaStreamTestkit = "com.typesafe.akka" %% "akka-stream-testkit" % "2.4.7"

    final def akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % "2.4.7"

    final def akkaDistributedDataExperimental = "com.typesafe.akka" %% "akka-distributed-data-experimental" % "2.4.7"

    final def akkaTypedExperimental = "com.typesafe.akka" %% "akka-typed-experimental" % "2.4.7"

    final def akkaHttpCore = "com.typesafe.akka" %% "akka-http-core" % "2.4.7"

    final def akkaHttpTestkit = "com.typesafe.akka" %% "akka-http-testkit" % "2.4.7"

    final def akkaHttpExperimental = "com.typesafe.akka" %% "akka-http-experimental" % "2.4.7"

    final def akkaHttpJacksonExperimental = "com.typesafe.akka" %% "akka-http-jackson-experimental" % "2.4.7"

    final def akkaSprayJsonExperimental = "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.4.7"

    final def akkaHttpXMLExperimental = "com.typesafe.akka" %% "akka-http-xml-experimental" % "2.4.7"

    final def akkaPersistenceQueryExperimental = "com.typesafe.akka" %% "akka-persistence-query-experimental" % "2.4.7"

    final def reactiveMongo = "org.reactivemongo" %% "reactivemongo" % "0.11.14" withSources()

    final def jsoup = "org.jsoup" % "jsoup" % "1.8.1" withSources()

    final def typeSafeConfig = "com.typesafe" % "config" % "1.3.0" withSources()

    //these two dependencies have to be put together on the ClassPath
    final def scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.4.0" withSources()

    final def logbackClassic = "ch.qos.logback" % "logback-classic" % "1.1.7" withSources()

    def resolvers: Seq[MavenRepository] = Seq(
      "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
      "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
    )
  }

  /**
    * All predefined test dependencies should go in here.
    */
  object test {
    final def scalaTest = "org.scalatest" %% "scalatest" % "2.2.6" % "test" withSources()

    final def scalaCheck = "org.scalacheck" %% "scalacheck" % "1.12.2" % "test" withSources()

    final def scalaMock = "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test" withSources()

    final def sprayTestKit = "io.spray" %% "spray-testkit" % "1.3.2" % "test" withSources()

    def resolvers: Seq[MavenRepository] = Seq()
  }

}