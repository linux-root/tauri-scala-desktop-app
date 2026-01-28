import sbtwelcome._
import scala.Console._

/**
 * Logo generated on [[https://patorjk.com]]
 */

logo :=
  s"""
     |   ▄▖    ▘    
     |   ▐ ▌▌▛▘▌▀▌▛▌
     |   ▐ ▙▌▌ ▌█▌▌▌
     |     ▄▌       
     |     
     |Version : ${version.value}
     |
     |$YELLOW Scala ${scalaVersion.value}$RESET
     | 
     |""".stripMargin

usefulTasks := Seq(
  UsefulTask("dev", "Start both ~fastOptJS and Vite dev server concurrently").alias("preview"),
  UsefulTask("mac", "Build macOS Tauri desktop app (.dmg, .zip)").alias("publishDist"),
  UsefulTask("win", "<not support yet>Build Windows Tauri desktop app (.exe)").alias("win"),
  UsefulTask("linux", "<not support yet>Build Linux Tauri desktop app (.AppImage, .deb)").alias("linux"),
  UsefulTask("welcome", "Menu").alias("h")
)

logoColor := MAGENTA
