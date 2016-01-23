package com.github.f1llon.coursera.cloudcapstone.scalding.job

import com.github.f1llon.coursera.cloudcapstone.scalding.DataModel.AirlineOnTimeSchema
import com.github.f1llon.coursera.cloudcapstone.utils.HadoopUtils._
import com.twitter.scalding._

/**
  * @author anton.v.filimonov@gmail.com
  * @since 1/22/16
  */
object RankOnTimeAirlinesIdJob {
  def apply(args: Args) = new RankOnTimeAirlinesIdJob(args)
}

class RankOnTimeAirlinesIdJob(args: Args) extends Job(args) {
  val srcDir = args("input")
  val (files, outputFile) = implicitly[Mode] match {
    case Hdfs(_, conf) =>
      val hdfs = HDFS(conf)
      val directory = hdfs.getWorkingDirectory
      (hdfs.subFiles(directory / Path(srcDir), ".csv"), directory.toString + "/output")
//    case Local(_) =>
//      (File(s".$srcDir").subFiles(".csv").map(_.getCanonicalPath), ".output/result.csv")
  }

  files.map(Csv(_, fields = AirlineOnTimeSchema, skipHeader = true).read)
    .reduce(_ ++ _)
    .groupBy(AirlineOnTimeSchema.AirlineID) {
      _.average(Symbol(AirlineOnTimeSchema.ArrDelay.toString))
    }
    .groupAll(_.sortBy(AirlineOnTimeSchema.ArrDelay))
    .write(Csv(outputFile, writeHeader = true))

  override def config: Map[AnyRef, AnyRef] = {
    super.config ++ Map("cascading.app.appjar.path" -> "/home/hadoop/jars/cloud-computing-capstone-project_2.11-1.0-one-jar.jar")
  }
}


