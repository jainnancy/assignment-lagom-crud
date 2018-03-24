//name := "assignment-lagom-crud"
organization in ThisBuild := "edu.knoldus"

version in ThisBuild := "0.1"

scalaVersion := "2.12.4"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"

lazy val `crud-lagom` = (project in file("."))
  .aggregate(`crud-lagom-api`,`crud-lagom-impl`)

lazy val `crud-lagom-api` = (project in file("crud-lagom-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `crud-lagom-impl` = (project in file("crud-lagom-impl"))
  .settings(
    libraryDependencies ++= Seq(
      macwire
    )
  )
  .enablePlugins(LagomScala)
  .dependsOn(`crud-lagom-api`)