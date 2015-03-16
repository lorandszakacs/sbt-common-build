package com.lorandszakacs.sbt.commonbuild.plugin

//import sbt._
//import com.lorandszakacs.sbt.commonbuild.plugin.CommonBuildPlugin._

///**
// * @author Lorand Szakacs, lorand.szakacs@busymachines.com
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
