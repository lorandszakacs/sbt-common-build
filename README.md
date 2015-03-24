sbt Common Build Plugin
================

A straightforward way of creating sbt build definitions.

## Current version

`0.1.0`

## Using it

Add `addSbtPlugin("com.lorandszakacs" % "sbt-common-build-plugin" % "0.1.1")` to the `project/plugins.sbt` file of your project.

## Example

We create a `project/*.scala` file with the following content:

```scala
import sbt._
import com.lorandszakacs.sbt.commonbuild.plugin.CommonBuildPlugin._

object SampleBuild extends CommonBuild {

  lazy val root = Project(
    "sample-project",
    file("."),
    settings = commonBuildSettings
  )

  override def projectVersion: String = "0.1.0"

  override def devDependencies: Seq[ModuleID] = Seq(
    dev.typeSafeConfig
  )

  override def runtimeDependencies: Seq[ModuleID] = Nil

  override def otherDependencies: Seq[ModuleID] = Nil

  override def testDependencies: Seq[ModuleID] = Seq(
    test.scalaTest
  )

  override def pluginSettings: Seq[Setting[_]] = Nil

  override def otherSettings: Seq[Setting[_]] = Nil

  override def publishingInfo: Option[PublishInfo] = Some(
    PublishInfo(
      resolver = publishing.typesafeResolver,
      license = publishing.apacheLicense,
      homepage = "www.example.com",
      scm = Scm(
        url = "https://github.com/example/example",
        connection = "scm:git@github.com:example/example.git"
      ),
      developer = publishing.lorandszakacs
    )
  )
}

```