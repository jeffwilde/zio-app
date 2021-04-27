ThisBuild / scalaVersion := "2.13.5"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

nativeImageOptions ++= List(
  "--no-fallback",
  "--allow-incomplete-classpath",
  "--initialize-at-build-time",
  "--initialize-at-run-time=org.jline.terminal.impl",
  "-H:ReflectionConfigurationFiles=../../src/main/resources/reflection-config.json",
  "--report-unsupported-elements-at-runtime"
)

val zioVersion = "1.0.7"

lazy val root = (project in file("."))
  .enablePlugins(NativeImagePlugin)
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "zio-app",
    libraryDependencies ++= Seq(
      "dev.zio"                  %% "zio"         % zioVersion,
      "dev.zio"                  %% "zio-process" % "0.3.0",
      "dev.zio"                  %% "zio-streams" % zioVersion,
      "dev.zio"                  %% "zio-test"    % zioVersion % Test,
      "org.jline"                 % "jline"       % "3.19.0",
      "org.foundweekends.giter8" %% "giter8-lib"  % "0.13.1",
      "log4j"                     % "log4j"       % "1.2.15"
    ),
    Compile / mainClass := Some("zio.app.Main"),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
