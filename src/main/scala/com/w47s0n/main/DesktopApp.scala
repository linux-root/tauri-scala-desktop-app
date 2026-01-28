package com.w47s0n.main

import tyrian.Html.*
import tyrian.*
import cats.effect.IO
import com.softwaremill.quicklens.*
import scala.scalajs.js
import scala.scalajs.js.annotation.*
import scala.concurrent.ExecutionContext.Implicits.global
import com.w47s0n.model.*
import com.w47s0n.view.DashboardLayout
import com.w47s0n.util.*
import com.w47s0n.util.TauriAPI.PromiseOps
import com.w47s0n.page.*
import com.w47s0n.service.GitHubService

@JSExportTopLevel("TyrianApp")
object DesktopApp extends TyrianIOApp[Msg, Model]:

  def main(args: Array[String]): Unit = launch("app")

  def router: Location => Msg = _ => Msg.NoOp

  def init(flags: Map[String, String]): (Model, Cmd[IO, Msg]) =
    val initState = Model.init
    (initState, Cmd.None)

  def update(model: Model): Msg => (Model, Cmd[IO, Msg]) =
    case Msg.NoOp => (model, Cmd.None)

    case Msg.ToggleDarkMode =>
      (model.toggleDarkMode, Cmd.None)

    case Msg.LogMessage(msg) =>
      (model, PrettyLogger.info(msg))

    case Msg.UpdateUsername(username) =>
      (model.modify(_.loginForm.username).setTo(username)
            .modify(_.loginForm.error).setTo(None), Cmd.None)

    case Msg.UpdatePassword(password) =>
      (model.modify(_.loginForm.password).setTo(password)
            .modify(_.loginForm.error).setTo(None), Cmd.None)

    case Msg.SubmitLogin =>
      val updatedModel = model.modify(_.loginForm.isLoading).setTo(true)
                              .modify(_.loginForm.error).setTo(None)
      val cmd = Authentication.authenticate(
        model.loginForm.username,
        model.loginForm.password,
        Msg.LoginSuccess,
        Msg.LoginError.apply
      )
      (updatedModel, cmd)

    case Msg.LoginSuccess =>
      val updatedModel = model.modify(_.isAuthenticated).setTo(true)
                              .modify(_.loginForm.isLoading).setTo(false)
      (updatedModel, Cmd.Emit(Msg.NavigateTo(Page.Home)) |+| PrettyLogger.success("Login successful!"))

    case Msg.LoginError(message) =>
      (model.modify(_.loginForm.isLoading).setTo(false)
            .modify(_.loginForm.error).setTo(Some(message)),
       PrettyLogger.error(s"Login failed: $message"))

    case Msg.Logout =>
      (model.modify(_.isAuthenticated).setTo(false)
            .modify(_.loginForm.username).setTo("")
            .modify(_.loginForm.password).setTo("")
            .modify(_.loginForm.error).setTo(None)
            .modify(_.currentPage).setTo(Page.Login),
       PrettyLogger.info("Logged out successfully"))

    case Msg.NavigateTo(page) =>
      if (page.isSecured && !model.isAuthenticated)
      then (model.modify(_.currentPage).setTo(Page.Login), PrettyLogger.info("Please login first"))
      else (model.modify(_.currentPage).setTo(page), page.beforeEnter(model))

    case Msg.OpenFileDialog =>
      val updatedModel = model.modify(_.fileUploadState.isLoading).setTo(true)
                              .modify(_.fileUploadState.error).setTo(None)

      val options = TauriAPI.OpenDialogOptions(
        title = Some("Select a text file"),
        filters = Some(Seq(
          TauriAPI.DialogFilter("Text Files", "txt", "md", "log", "json", "xml", "csv")
        )),
        multiple = false
      )

      val cmd = Cmd.Run(
        IO.fromFuture(IO(TauriAPI.openDialog(options).toScalaFuture.map {
          case path: String => path
          case arr: js.Array[String] => arr.head
          case _ => ""
        })).attempt.map {
          case Right(path) if path.nonEmpty => Msg.FileSelected(path)
          case Right(_) => Msg.FileError("No file selected")
          case Left(e) => Msg.FileError(s"Failed to open file dialog: ${e.getMessage}")
        }
      )

      (updatedModel, cmd)

    case Msg.FileSelected(path) =>
      val updatedModel = model.modify(_.fileUploadState.selectedFilePath).setTo(Some(path))

      val cmd = Cmd.Run(
        IO.fromFuture(IO(TauriAPI.readTextFile(path).toScalaFuture)).attempt.map {
          case Right(content) => Msg.FileContentLoaded(content)
          case Left(e) => Msg.FileError(s"Failed to read file: ${e.getMessage}")
        }
      )

      (updatedModel, cmd)

    case Msg.FileContentLoaded(content) =>
      (model.modify(_.fileUploadState.fileContent).setTo(Some(content))
            .modify(_.fileUploadState.isLoading).setTo(false),
       PrettyLogger.success("File loaded successfully!"))

    case Msg.FileError(message) =>
      (model.modify(_.fileUploadState.isLoading).setTo(false)
            .modify(_.fileUploadState.error).setTo(Some(message)),
       PrettyLogger.error(message))

    case Msg.FetchGitHubRepos =>
      (model.modify(_.gitHubReposState.isLoading).setTo(true)
            .modify(_.gitHubReposState.error).setTo(None),
       GitHubService.fetchRepositories)

    case Msg.GitHubReposLoaded(repos) =>
      (model.modify(_.gitHubReposState.repos).setTo(repos)
            .modify(_.gitHubReposState.isLoading).setTo(false),
       PrettyLogger.success(s"Loaded ${repos.length} GitHub repositories"))

    case Msg.GitHubReposError(message) =>
      (model.modify(_.gitHubReposState.isLoading).setTo(false)
            .modify(_.gitHubReposState.error).setTo(Some(message)),
       PrettyLogger.error(s"GitHub API error: $message"))

  def view(model: Model): Html[Msg] =
    val pageContent = model.currentPage.render(model)
    if (model.currentPage == Page.Login)
    then pageContent
    else DashboardLayout(pageContent, model)

  def subscriptions(model: Model): Sub[IO, Msg] = Sub.None
