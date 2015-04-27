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
package com.lorandszakacs.sbt.commonbuild.plugin

//import sbt._
//import com.lorandszakacs.sbt.commonbuild.plugin.CommonBuildPlugin._

///**
// * @author Lorand Szakacs, lsz@lorandszakacs.com
// * @since 16 Mar 2015
// *
// */
//object SampleBuild extends CommonBuild {
//
//  lazy val root = Project(
//    "sample-project",
//    file("."),
//    settings = commonBuildSettings
//  )
//
//  override def projectVersion: String = "0.1.0"
//
//  override def devDependencies: Seq[ModuleID] = Seq(
//    dev.typeSafeConfig
//  )
//
//  override def runtimeDependencies: Seq[ModuleID] = Nil
//
//  override def otherDependencies: Seq[ModuleID] = Nil
//
//  override def testDependencies: Seq[ModuleID] = Seq(
//    test.scalaTest
//  )
//
//  override def pluginSettings: Seq[Setting[_]] = Nil
//
//  override def otherSettings: Seq[Setting[_]] = Nil
//
//  override def publishingInfo: Option[PublishInfo] = Some(
//    PublishInfo(
//      resolver = publishing.typesafeResolver,
//      license = publishing.apacheLicense,
//      homepage = "www.example.com",
//      scm = Scm(
//        url = "https://github.com/example/example",
//        connection = "scm:git@github.com:example/example.git"
//      ),
//      developer = publishing.lorandszakacs
//    )
//  )
//}
