import Dependencies._

Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "net.nergi"
ThisBuild / organizationName := "nergi"

lazy val sbtAssemblySettings = baseAssemblySettings ++ Seq(
  assembly / assemblyOutputPath    := baseDirectory.value / "wacc-compiler.jar",
  assembly / assemblyMergeStrategy := {
    case PathList("META-INF", _ @_*) => MergeStrategy.discard
    case _                           => MergeStrategy.first
  }
)

lazy val root = (project in file("."))
  .settings(
    name                := "EternityWrapper",
    sbtAssemblySettings,
    scalacOptions      ++= Seq("-deprecation", "-unchecked", "-feature"),
    libraryDependencies += scalaFx,
    libraryDependencies += scalaTest % Test
  )
