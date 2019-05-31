name := "RedisProtocolGenerator"
version := "0.1"
scalaVersion := "2.12.8"

libraryDependencies ++= {
  val Json4sVersion = "3.6.6"
  Seq(
    "org.json4s" %% "json4s-native" % Json4sVersion,
    "org.json4s" %% "json4s-ext" % Json4sVersion,
    "com.github.etaty" %% "rediscala" % "1.8.0",
    "com.github.kstyrc" % "embedded-redis" % "0.6" % "test",
    "org.scalatest" %% "scalatest" % "3.0.0" % "test"
  )
}