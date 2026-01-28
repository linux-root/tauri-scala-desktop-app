package com.w47s0n.util

import tyrian.Cmd
import tyrian.cmds.*
import cats.effect.IO
import scala.concurrent.duration.*

object Authentication:

  def authenticate[A](username: String, password: String, onSuccess: A, onFailure: String => A): Cmd[IO, A] =
    val isValid = username == "scala" && password == "lover"

    Cmd.Run(
      IO.sleep(1.second) *> IO.pure {
        if isValid then onSuccess
        else onFailure("Invalid username or password")
      }
    ) |+| PrettyLogger.info(s"Mocking authentication for username: $username (valid: $isValid)")
