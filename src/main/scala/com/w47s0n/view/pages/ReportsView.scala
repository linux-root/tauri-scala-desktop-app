package com.w47s0n.view.pages

import tyrian.Html.*
import tyrian.SVG.*
import tyrian.Html
import com.w47s0n.model.Msg

object ReportsView:

  def apply(): Html[Msg] =
    div()(
      div(cls := "mb-8")(
        h1(cls := "text-3xl font-bold text-base-content mb-2")("Reports"),
        p(cls := "text-base-content/60")("Generate and view detailed reports")
      ),
      div(cls := "grid grid-cols-1 md:grid-cols-4 gap-6 mb-8")(
        statCard("Total Reports", "24"),
        statCard("Generated Today", "5"),
        statCard("Scheduled", "8"),
        statCard("Pending", "3")
      ),
      div(cls := "card bg-base-200 border border-base-300 shadow")(
        div(cls := "card-body")(
          div(cls := "flex items-center justify-between mb-6")(
            h2(cls := "text-xl font-semibold text-base-content")("Recent Reports"),
            button(cls := "btn btn-primary btn-sm")("Generate New")
          ),
          div(cls := "space-y-4")(
            reportItem("Monthly Sales Report", "December 2024", "Completed", "badge-success"),
            reportItem("User Activity Summary", "Q4 2024", "Completed", "badge-success"),
            reportItem("Revenue Forecast", "January 2025", "In Progress", "badge-warning"),
            reportItem("Customer Feedback Analysis", "Weekly", "Scheduled", "badge-primary")
          )
        )
      )
    )

  private def statCard(label: String, value: String): Html[Msg] =
    div(cls := "card bg-base-200 border border-base-300 shadow")(
      div(cls := "card-body text-center")(
        div(cls := "text-3xl font-bold text-base-content mb-2")(value),
        div(cls := "text-base-content/60 text-sm")(label)
      )
    )

  private def reportItem(title: String, period: String, status: String, badgeClass: String): Html[Msg] =
    div(cls := "flex items-center justify-between p-4 rounded-lg bg-base-300/30 hover:bg-base-300/50 transition-colors")(
      div(cls := "flex items-center gap-4")(
        div(cls := "w-10 h-10 rounded-lg bg-primary/10 flex items-center justify-center")(
          svg(cls := "w-5 h-5 text-primary", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
            path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
              attribute("d", "M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"))
          )
        ),
        div()(
          div(cls := "font-medium text-base-content")(title),
          div(cls := "text-sm text-base-content/60")(period)
        )
      ),
      div(cls := "flex items-center gap-3")(
        span(cls := badgeClass)(status),
        button(cls := "text-base-content/60 hover:text-base-content transition-colors")(
          svg(cls := "w-5 h-5", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
            path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
              attribute("d", "M4 16v1a3 3 0 003 3h10a3 3 0 003-3v-1m-4-4l-4 4m0 0l-4-4m4 4V4"))
          )
        )
      )
    )
