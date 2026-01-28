package com.w47s0n.service

import tyrian.Cmd
import cats.effect.IO
import cats.implicits.*
import com.w47s0n.model.{GitHubRepo, Msg}
import sttp.client4.*
import sttp.client4.impl.cats.FetchCatsBackend
import sttp.client4.circe.*
import sttp.model.Uri
import io.circe.generic.auto.*

object GitHubService:

  val repositories = List(
    "PurpleKingdomGames/tyrian",
    "scala-js/scala-js",
    "mattlianje/layoutz",
    "scalameta/nvim-metals",
    "business4s/workflows4s",
    "apache/pekko"
  )

  val fetchRepositories: Cmd[IO, Msg] =
    val backend = FetchCatsBackend[IO]()

    val httpCalls = repositories.traverse { repoPath =>
      val url = Uri.unsafeParse(s"https://api.github.com/repos/$repoPath")
      val request = basicRequest
        .get(url)
        .header("Accept", "application/vnd.github.v3+json")
        .header("User-Agent", "tyrian-tauri-scala-app")
        .response(asJson[GitHubRepo])

      backend
        .send(request)
        .map { response =>
          response.body match {
            case Left(error) =>
              scala.scalajs.js.Dynamic.global.console.error(
                s"Error fetching $repoPath: statusCode: ${response.code}, response: ${error}"
              )
              None
            case Right(repo) =>
              Some(repo)
          }
        }
        .handleError { e =>
          scala.scalajs.js.Dynamic.global.console.error(s"Fatal error for $repoPath: ${e.getMessage}")
          None
        }
    }.map { results =>
      val repos = results.flatten
      if repos.isEmpty then Msg.GitHubReposError("Failed to fetch repository data")
      else Msg.GitHubReposLoaded(repos)
    }

    Cmd.Run(httpCalls)
