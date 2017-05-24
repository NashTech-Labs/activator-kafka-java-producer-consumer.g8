name := """activator-kafka-java-producer-consumer"""

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "0.10.0.0"
)
