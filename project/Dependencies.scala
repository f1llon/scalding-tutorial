/*
 * Copyright (c) 2012 SnowPlow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
import sbt._

object Dependencies {
  val resolutionRepos = Seq(
    "Concurrent Maven Repo" at "http://conjars.org/repo" // For Scalding, Cascading etc
  )

  object V {
    val scalding  = "0.15.0"
    val hadoop    = "2.7.1"
    val cascading = "2.7.1"
  }

  object Libraries {
    val cascadingCore = "cascading" % "cascading-core" % V.cascading
    val cascadingLocal = "cascading" %  "cascading-local" % V.cascading
    val cascadingHadoop = "cascading" %  "cascading-hadoop2-mr1" % V.cascading
    val scaldingCore = "com.twitter" %%  "scalding-core" % V.scalding exclude( "cascading", "cascading-local" ) exclude( "cascading", "cascading-hadoop" )
    val hadoopCore   = "org.apache.hadoop" % "hadoop-common" % V.hadoop % "provided"
    val hadoopClientCore   = "org.apache.hadoop" % "hadoop-mapreduce-client-core" % V.hadoop % "provided"
  }
}
