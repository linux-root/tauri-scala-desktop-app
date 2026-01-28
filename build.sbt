import sbt.io.Path.relativeTo

lazy val tauriBuildMac   = taskKey[Unit]("Build macOS Tauri desktop app")
lazy val tauriBuildWin   = taskKey[Unit]("Build Windows Tauri desktop app")
lazy val tauriBuildLinux = taskKey[Unit]("Build Linux Tauri desktop app")

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    organization := "com.w47s0n",
    name         := "tyrian-tauri-scala",
    version      := "0.1.0",
    scalaVersion := "3.8.1",
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    scalaJSUseMainModuleInitializer := true,
    // Source maps seem to be broken with bundler
    Compile / fastOptJS / scalaJSLinkerConfig ~= { _.withSourceMap(false) },
    Compile / fullOptJS / scalaJSLinkerConfig ~= { _.withSourceMap(false) },
    libraryDependencies ++= Seq(
      "io.indigoengine"               %%% "tyrian-io"     % Dependencies.Tyrian,
      "com.softwaremill.sttp.client4" %%% "core"          % Dependencies.Sttp,
      "com.softwaremill.sttp.client4" %%% "cats"          % Dependencies.Sttp,
      "com.softwaremill.sttp.client4" %%% "circe"         % Dependencies.Sttp,
      "io.circe"                      %%% "circe-generic" % Dependencies.Circe,
      "com.softwaremill.quicklens"    %%% "quicklens"     % Dependencies.Quicklens
    )
  )
  .settings(
    jsTool := JSToolConfig(
      installPackagesCommand = ScalaJsCli.Cmd("bun install", "\\d+\\spackages\\sinstalled".r),
      dev = DevConfig(
        command = ScalaJsCli.Cmd("bun run tauri dev", "Finished".r),
        startupMessage = """|Starting development environment:
                            |Scala.js compiler: Starting in watch mode...
                            |Vite dev server: Starting...""".stripMargin,
        successMessage = """|Development environment ready!
                            |Press Ctrl+C to stop""".stripMargin
      ),
      build = BuildConfig(
        command = ScalaJsCli.Cmd("bun run tauri build", "Finished".r),
        startupMessage = "Building tauri app",
        successMessage = "Tauri app built"
      )
    )
  )
