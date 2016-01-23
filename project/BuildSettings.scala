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
import Keys._
import sbtassembly.{MergeStrategy, AssemblyKeys}

object BuildSettings {

  // Basic settings for our app
  lazy val basicSettings = Seq[Setting[_]](
    organization := "Concurrent Inc.",
    version := "0.15.0", // -> follow the release numbers of scalding
    description := "The scalding tutorial as an SBT project",
    scalaVersion := "2.11.7",
    scalacOptions := Seq("-deprecation", "-encoding", "utf8"),
    resolvers ++= Dependencies.resolutionRepos
  )


  // sbt-assembly settings for building a fat jar
  import AssemblyKeys._

  lazy val sbtAssemblySettings = Seq(

    assemblyExcludedJars in assembly := {
      val cp = (fullClasspath in assembly).value
      cp.foreach(x => println(x.data.getName))
      val excludes = Set[String](
        "minlog-1.2.jar", // Otherwise causes conflicts with Kyro (which bundles it)
        "janino-2.7.5.jar" // Janino includes a broken signature, and is not needed anyway
      )
      cp filter { jar => excludes(jar.data.getName) }
    }

/*
    assemblyMergeStrategy in assembly := {
      case "project.clj" => MergeStrategy.discard
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    }
*/
  )

  lazy val buildSettings = basicSettings ++ sbtAssemblySettings
}
