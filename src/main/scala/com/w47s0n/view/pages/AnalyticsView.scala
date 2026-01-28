package com.w47s0n.view.pages

import tyrian.Html.*
import tyrian.SVG.*
import tyrian.Html
import com.w47s0n.model.Msg

object AnalyticsView:

  def apply(): Html[Msg] =
    div()(
      div(cls := "flex flex-col md:flex-row md:items-center md:justify-between mb-8 gap-4")(
        div()(
          h1(cls := "text-3xl font-semibold text-base-content mb-2")("Analytics"),
          p(cls := "text-base-content/60")("Monitor and analyze key metrics")
        ),
        div(attribute("role", "tablist"), cls := "tabs tabs-boxed")(
          tab("Today", true),
          tab("Week", false),
          tab("Month", false),
          tab("Year", false)
        )
      ),
      div(cls := "stats stats-vertical lg:stats-horizontal shadow w-full mb-8 bg-base-200 border border-base-300")(
        statCard("Page Views", "145,892", "↗︎ 8.3%", "text-primary"),
        statCard("Unique Visitors", "52,341", "↗︎ 12.7%", "text-secondary"),
        statCard("Bounce Rate", "42.5%", "↘︎ 3.2%", "text-accent"),
        statCard("Avg. Duration", "5m 23s", "↗︎ 1.8%", "text-info")
      ),
      div(cls := "card bg-base-200 border border-base-300 shadow mb-8")(
        div(cls := "card-body")(
          h2(cls := "card-title text-lg mb-6")("Traffic Sources"),
          div(cls := "space-y-4")(
            progressBar("Direct", 45, "primary"),
            progressBar("Organic Search", 32, "secondary"),
            progressBar("Social Media", 15, "accent"),
            progressBar("Referral", 8, "info")
          )
        )
      ),
      div(cls := "card bg-base-200 border border-base-300 shadow mb-8")(
        div(cls := "card-body")(
          h2(cls := "card-title text-lg mb-4")(
            chartIcon,
            text("Traffic Overview")
          ),
          div(cls := "h-80 flex items-center justify-center bg-base-100 rounded-lg border border-base-300")(
            div(cls := "text-center")(
              svg(cls := "w-20 h-20 mx-auto mb-4 text-base-content/20", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
                path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "1.5"),
                  attribute("d", "M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z"))
              ),
              div(cls := "text-base-content/60 mb-2")("Chart Visualization"),
              p(cls := "text-base-content/40 text-sm max-w-md")(
                "Chart placeholder for data visualization"
              ),
              button(cls := "btn btn-ghost btn-sm mt-4 gap-2")(
                svg(cls := "w-4 h-4", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
                  path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
                    attribute("d", "M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15"))
                ),
                text("Refresh Data")
              )
            )
          )
        )
      ),
      div(cls := "card bg-base-200 border border-base-300 shadow")(
        div(cls := "card-body")(
          h2(cls := "card-title text-lg mb-4")("Top Pages"),
          div(cls := "overflow-x-auto")(
            table(cls := "table table-zebra")(
              thead()(
                tr()(
                  th()("Page"),
                  th()("Views"),
                  th()("Bounce Rate"),
                  th()("Avg. Time"),
                  th()("Status")
                )
              ),
              tbody()(
                tableRow("/dashboard", "45,234", "35%", "4m 12s", "success"),
                tableRow("/products", "32,456", "42%", "3m 45s", "warning"),
                tableRow("/checkout", "18,923", "28%", "5m 33s", "success"),
                tableRow("/about", "12,456", "55%", "2m 18s", "error"),
                tableRow("/contact", "8,234", "38%", "3m 52s", "success")
              )
            )
          )
        )
      )
    )

  private def tab(label: String, active: Boolean): Html[Msg] =
    val roleAttr = attribute("role", "tab")
    val classAttr = cls := (if active then "tab tab-active" else "tab")
    a(roleAttr, classAttr)(label)

  private def statCard(title: String, value: String, change: String, valueClass: String): Html[Msg] =
    div(cls := "stat")(
      div(cls := "stat-title text-base-content/70")(title),
      div(cls := s"stat-value $valueClass")(value),
      div(cls := "stat-desc font-semibold")(
        if change.contains("↗︎") then
          span(cls := "text-success")(text(change + " vs last period"))
        else
          span(cls := "text-error")(text(change + " vs last period"))
      )
    )

  private def progressBar(label: String, percentage: Int, color: String): Html[Msg] =
    div()(
      div(cls := "flex justify-between mb-2")(
        span(cls := "text-base-content/80")(label),
        span(cls := "text-base-content/60 text-sm")(s"$percentage%")
      ),
      div(cls := "w-full bg-base-200 rounded-full h-3")(
        div(
          cls := s"bg-$color h-3 rounded-full transition-all",
          attribute("style", s"width: $percentage%")
        )()
      )
    )

  private def tableRow(page: String, views: String, bounce: String, avgTime: String, status: String): Html[Msg] =
    tr(cls := "hover")(
      td()(
        div(cls := "flex items-center gap-2")(
          svg(cls := "w-4 h-4 text-base-content/60", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
            path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
              attribute("d", "M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"))
          ),
          span()(page)
        )
      ),
      td()(
        span(cls := "font-semibold")(views)
      ),
      td()(bounce),
      td()(avgTime),
      td()(
        span(cls := s"badge badge-$status")()
      )
    )

  private def chartIcon: Html[Msg] =
    svg(cls := "w-6 h-6", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z"))
    )
