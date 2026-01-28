package com.w47s0n.view.pages

import tyrian.Html.*
import tyrian.SVG.*
import tyrian.Html
import com.w47s0n.model.{Msg, Model, GitHubRepo}
import com.w47s0n.page.Page

object DashboardView:

  def apply(model: Model): Html[Msg] =
    div()(
      heroSection,
      metricsSection,
      gitHubReposSection(model),
      quickStatsGrid,
      chartsRow,
      activityFeed
    )

  private def heroSection: Html[Msg] =
    div(cls := "mb-8")(
      div(cls := "flex items-center justify-between")(
        div()(
          h1(cls := "text-3xl font-semibold text-base-content mb-2")(
            "Dashboard"
          ),
          p(cls := "text-base-content/60")(
            "Monitor key metrics and system performance"
          )
        ),
        div(cls := "flex gap-3")(
          button(
            cls := "btn btn-outline btn-sm gap-2",
            onClick(Msg.NavigateTo(Page.FileUpload))
          )(
            uploadIcon,
            text("File Upload")
          ),
          button(
            cls := "btn btn-primary btn-sm gap-2",
            onClick(Msg.NavigateTo(Page.Analytics))
          )(
            chartIcon,
            text("Analytics")
          )
        )
      )
    )

  private def metricsSection: Html[Msg] =
    div(cls := "stats stats-vertical lg:stats-horizontal shadow w-full mb-8 bg-base-200 border border-base-300")(
      statCard("Total Revenue", "$124,562", "↗︎ 12.5%", "stat-value", "text-success"),
      statCard("Active Users", "8,452", "↗︎ 5.2%", "stat-value", "text-success"),
      statCard("Conversion Rate", "3.24%", "↘︎ 0.8%", "stat-value", "text-error"),
      statCard("Avg. Session", "4m 32s", "↗︎ 2.1%", "stat-value", "text-success")
    )

  private def statCard(title: String, value: String, change: String, valueClass: String, changeClass: String): Html[Msg] =
    div(cls := "stat")(
      div(cls := "stat-title text-base-content/70")(title),
      div(cls := valueClass)(value),
      div(cls := s"stat-desc $changeClass font-semibold")(text(change + " vs last month"))
    )

  private def gitHubReposSection(model: Model): Html[Msg] =
    div(cls := "mb-8")(
      div(cls := "flex items-center justify-between mb-4")(
        h2(cls := "text-xl font-semibold text-base-content")("Featured Scala Projects"),
        if model.gitHubReposState.isLoading then
          span(cls := "loading loading-spinner loading-sm")()
        else
          button(cls := "btn btn-ghost btn-sm", onClick(Msg.FetchGitHubRepos))(
            svg(cls := "w-4 h-4", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
              path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
                attribute("d", "M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15"))
            )
          )
      ),
      model.gitHubReposState.error match
        case Some(error) =>
          div(cls := "alert alert-error")(
            svg(cls := "w-5 h-5 shrink-0", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
              path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
                attribute("d", "M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"))
            ),
            span(error)
          )
        case None =>
          if model.gitHubReposState.repos.isEmpty && model.gitHubReposState.isLoading then
            div(cls := "flex items-center justify-center py-12")(
              span(cls := "loading loading-spinner loading-lg")()
            )
          else if model.gitHubReposState.repos.isEmpty then
            div(cls := "text-center py-12 text-base-content/60")("No repositories loaded")
          else
            div(cls := "grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4")(
              model.gitHubReposState.repos.map(gitHubRepoCard)
            )
    )

  private def gitHubRepoCard(repo: GitHubRepo): Html[Msg] =
    a(
      href := repo.htmlUrl,
      target := "_blank",
      cls := "card bg-base-200 border border-base-300 shadow hover:shadow-lg hover:border-primary/50 transition-all"
    )(
      div(cls := "card-body")(
        div(cls := "flex items-start justify-between mb-2")(
          div(cls := "flex-1")(
            h3(cls := "font-semibold text-base-content text-sm mb-1")(repo.name),
            p(cls := "text-xs text-base-content/60 line-clamp-2")(
              repo.description.getOrElse("No description")
            )
          ),
          repo.language.map { lang =>
            span(cls := "badge badge-primary badge-sm")(lang)
          }.getOrElse(text(""))
        ),
        div(cls := "flex items-center gap-4 text-xs text-base-content/60 mt-3")(
          div(cls := "flex items-center gap-1")(
            svg(cls := "w-4 h-4", attribute("fill", "currentColor"), attribute("viewBox", "0 0 20 20"))(
              path(attribute("d", "M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"))
            ),
            span()(formatNumber(repo.stars))
          ),
          div(cls := "flex items-center gap-1")(
            svg(cls := "w-4 h-4", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
              path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
                attribute("d", "M8.684 13.342C8.886 12.938 9 12.482 9 12c0-.482-.114-.938-.316-1.342m0 2.684a3 3 0 110-2.684m0 2.684l6.632 3.316m-6.632-6l6.632-3.316m0 0a3 3 0 105.367-2.684 3 3 0 00-5.367 2.684zm0 9.316a3 3 0 105.368 2.684 3 3 0 00-5.368-2.684z"))
            ),
            span()(formatNumber(repo.forks))
          ),
          div(cls := "flex items-center gap-1")(
            svg(cls := "w-4 h-4", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
              path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
                attribute("d", "M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"))
            ),
            span()(formatNumber(repo.openIssues))
          )
        )
      )
    )

  private def formatNumber(n: Int): String =
    if n >= 1000 then
      f"${n / 1000.0}%.1fk"
    else
      n.toString

  private def quickStatsGrid: Html[Msg] =
    div(cls := "grid grid-cols-1 lg:grid-cols-3 gap-6 mb-8")(
      radialProgressCard("System Health", 87, "primary"),
      radialProgressCard("Server Uptime", 99, "success"),
      radialProgressCard("API Performance", 72, "warning")
    )

  private def radialProgressCard(label: String, value: Int, color: String): Html[Msg] =
    div(cls := "card bg-base-200 border border-base-300 shadow")(
      div(cls := "card-body items-center text-center")(
        div(
          cls := s"radial-progress text-$color",
          attribute("style", s"--value:$value; --size:6rem; --thickness:0.4rem;"),
          attribute("role", "progressbar")
        )(
          span(cls := "text-xl font-semibold")(s"$value%")
        ),
        h3(cls := "font-semibold text-base-content mt-4")(label),
        p(cls := "text-base-content/50 text-xs")("Last 24 hours")
      )
    )

  private def chartsRow: Html[Msg] =
    div(cls := "grid grid-cols-1 lg:grid-cols-2 gap-6 mb-8")(
      chartCard("Revenue Trends", "Line chart showing monthly revenue growth", revenueIcon),
      chartCard("User Activity", "Bar chart showing daily active users", activityIcon)
    )

  private def chartCard(title: String, description: String, icon: Html[Msg]): Html[Msg] =
    div(cls := "card bg-base-200 border border-base-300 shadow")(
      div(cls := "card-body")(
        h3(cls := "card-title text-lg mb-4")(
          icon,
          text(title)
        ),
        div(cls := "h-64 flex items-center justify-center bg-base-100 rounded-lg border border-base-300")(
          div(cls := "text-center")(
            svg(cls := "w-16 h-16 mx-auto mb-3 text-base-content/20", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
              path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "1.5"),
                attribute("d", "M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z"))
            ),
            div(cls := "text-base-content/60 text-sm")(description),
            div(cls := "text-base-content/40 text-xs mt-2")("Chart placeholder")
          )
        )
      )
    )

  private def activityFeed: Html[Msg] =
    div(cls := "card bg-base-200 border border-base-300 shadow")(
      div(cls := "card-body")(
        div(cls := "flex items-center justify-between mb-6")(
          h2(cls := "card-title text-lg")("Recent Activity"),
          button(cls := "btn btn-ghost btn-sm")("View All")
        ),
        ul(cls := "timeline timeline-vertical")(
          timelineItem("New user registration", "John Doe signed up", "2 min ago", "success"),
          timelineItem("Payment received", "Invoice #12345 paid", "15 min ago", "info"),
          timelineItem("System update", "Version 2.1.0 deployed", "1 hour ago", "warning"),
          timelineItem("Feature released", "Analytics dashboard launched", "3 hours ago", "success"),
          timelineItem("Data backup", "Daily backup completed successfully", "5 hours ago", "info")
        )
      )
    )

  private def timelineItem(title: String, desc: String, time: String, status: String): Html[Msg] =
    li()(
      div(cls := "timeline-start timeline-box bg-base-100 border-base-300")(
        div(cls := "font-semibold text-base-content text-sm")(title),
        div(cls := "text-xs text-base-content/60")(desc)
      ),
      div(cls := "timeline-middle")(
        div(cls := s"w-3 h-3 rounded-full bg-$status")()
      ),
      div(cls := "timeline-end")(
        span(cls := s"badge badge-ghost badge-sm text-xs")(time)
      ),
      hr(cls := "bg-base-300")
    )

  private def uploadIcon: Html[Msg] =
    svg(cls := "w-5 h-5", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M7 16a4 4 0 01-.88-7.903A5 5 0 1115.9 6L16 6a5 5 0 011 9.9M15 13l-3-3m0 0l-3 3m3-3v12"))
    )

  private def chartIcon: Html[Msg] =
    svg(cls := "w-5 h-5", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z"))
    )

  private def revenueIcon: Html[Msg] =
    svg(cls := "w-6 h-6", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M13 7h8m0 0v8m0-8l-8 8-4-4-6 6"))
    )

  private def activityIcon: Html[Msg] =
    svg(cls := "w-6 h-6", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z"))
    )
