package com.github.f1llon.coursera.cloudcapstone.utils

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path => HdfsPath}

/**
  * @author anton.v.filimonov@gmail.com
  * @since 1/22/16
  */
object HadoopUtils {

  object Path {
    def apply(path: String) = new HdfsPath(path)
  }

  object Configuration {
    def apply() = {
      val configuration= new Configuration()
      configuration.set("fs.defaultFS", "hdfs://localhost:9000")
      configuration.set("mapred.job.tracker","localhost:8032")
      configuration.set("mapreduce.framework.name","yarn")
      configuration
    }
  }


  object HDFS {
    def apply() = FileSystem.get(Configuration())

    def apply(conf: Configuration) = FileSystem.get(conf)
  }

  implicit class RichPath(path: HdfsPath) {
    def /(subPath: HdfsPath): HdfsPath = HdfsPath.mergePaths(path, subPath)

    def /(subPath: String): HdfsPath = path / Path(subPath)
  }

  implicit class RichHDFS(fs: FileSystem) {

    def subFiles(src: HdfsPath, suffix: String) = {
      fs.listStatus(src).toList.map(_.getPath.toString).filter(_.endsWith(suffix))
    }

    def writeFile(src: HdfsPath, dst: HdfsPath) = {
      fs.mkdirs(dst)
      fs.copyFromLocalFile(true, src, dst)
      s"${dst.toUri.getPath}/${src.getName}"
    }
  }

}
