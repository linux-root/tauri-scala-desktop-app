package com.w47s0n.page

import tyrian.Cmd
import tyrian.Html
import com.w47s0n.model.*
import com.w47s0n.view.pages.*
import cats.effect.IO
import com.w47s0n.util.PrettyLogger

enum Page(
  val render: Model => Html[Msg],
  val beforeEnter: Model => Cmd[IO, Msg] = _ => Cmd.None,
  val isSecured: Boolean = true
){
  case Login      extends Page(model => LoginView(model), isSecured = false)
  case Home       extends Page(
    model => DashboardView(model),
    model =>
      if model.gitHubReposState.repos.isEmpty  then
        import com.w47s0n.service.GitHubService
        PrettyLogger.info("Entering Dashboard page - fetching GitHub repos") |+| GitHubService.fetchRepositories
      else
        PrettyLogger.info("Entering Dashboard page")
  )
  case FileUpload extends Page(model => FileUploadView(model), _ => PrettyLogger.info("Entering File Upload page"))
  case Analytics  extends Page(model => AnalyticsView(), _ => PrettyLogger.info("Entering Analytics page"))
  case Reports    extends Page(model => ReportsView(), _ => PrettyLogger.info("Entering Reports page"))
  case Components extends Page(model => ComponentsGalleryView(), _ => PrettyLogger.info("Entering Components Gallery page"))
  case Settings   extends Page(model => SettingsView(), _ => PrettyLogger.info("Entering Settings page"))
}
