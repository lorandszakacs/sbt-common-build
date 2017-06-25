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

import com.lorandszakacs.sbt.commonbuild.core
import sbt._

/**
  * @author Lorand Szakacs, lsz@lorandszakacs.com
  * @since 16 Mar 2015
  *
  */
object CommonBuildPlugin extends AutoPlugin {

  lazy val common = core.CommonBuildCore

  type PublishInfo = core.PublishInfo
  lazy val PublishInfo = core.PublishInfo

  type OrganizationInfo = core.OrganizationInfo
  lazy val OrganizationInfo = core.OrganizationInfo

  type ProjectInfo = core.ProjectInfo
  lazy val ProjectInfo = core.ProjectInfo


  override def requires = sbt.plugins.JvmPlugin

  override def trigger: PluginTrigger = allRequirements
}
