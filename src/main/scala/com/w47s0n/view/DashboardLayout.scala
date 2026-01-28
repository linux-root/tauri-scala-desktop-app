package com.w47s0n.view

import tyrian.Html.*
import tyrian.SVG.*
import tyrian.Html
import com.w47s0n.model.{Msg, Model}
import com.w47s0n.page.Page

object DashboardLayout:

  def sidebar(currentPage: Page): Html[Msg] =
    div(cls := "sidebar")(
      div(cls := "p-4")(
        div(cls := "mb-8 px-2")(
          h2(cls := "text-xl font-bold gradient-text")("Tyrian Desktop")
        ),
        ul(cls := "menu bg-base-200 rounded-box space-y-1")(
          navItem("Dashboard", currentPage == Page.Home, Page.Home, dashboardIcon),
          navItem("File Upload", currentPage == Page.FileUpload, Page.FileUpload, fileUploadIcon),
          navItem("Analytics", currentPage == Page.Analytics, Page.Analytics, analyticsIcon),
          navItem("Reports", currentPage == Page.Reports, Page.Reports, reportsIcon),
          li(cls := "divider my-2")(),
          navItem("Components", currentPage == Page.Components, Page.Components, componentsIcon),
          navItem("Settings", currentPage == Page.Settings, Page.Settings, settingsIcon)
        ),
        div(cls := "absolute bottom-8 left-0 right-0 px-6")(
          div(cls := "card p-4")(
            div(cls := "flex items-center justify-between")(
              div(cls := "flex items-center gap-3")(
                div(cls := "w-10 h-10 rounded flex items-center justify-center bg-[#DC322F] p-1.5")(
                  scalaLogo
                ),
                div()(
                  div(cls := "text-sm font-medium text-base-content")("Scala Developer"),
                  div(cls := "text-xs text-base-content/60")("Admin")
                )
              ),
              button(
                onClick(Msg.Logout),
                cls := "p-2 rounded-lg hover:bg-gray-700/50 transition-colors text-gray-400 hover:text-gray-200",
                attribute("title", "Logout")
              )(
                svg(cls := "w-5 h-5", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
                  path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
                    attribute("d", "M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1"))
                )
              )
            )
          )
        )
      )
    )

  private def navItem(label: String, isActive: Boolean, page: Page, icon: Html[Msg]): Html[Msg] =
    val activeClass = if isActive then "active bg-primary text-primary-content" else ""
    li()(
      button(
        onClick(Msg.NavigateTo(page)),
        cls := s"$activeClass gap-3"
      )(
        icon,
        span()(label)
      )
    )

  private def dashboardIcon: Html[Msg] =
    svg(cls := "w-5 h-5", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"))
    )

  private def fileUploadIcon: Html[Msg] =
    svg(cls := "w-5 h-5", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M7 16a4 4 0 01-.88-7.903A5 5 0 1115.9 6L16 6a5 5 0 011 9.9M15 13l-3-3m0 0l-3 3m3-3v12"))
    )

  private def analyticsIcon: Html[Msg] =
    svg(cls := "w-5 h-5", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z"))
    )

  private def reportsIcon: Html[Msg] =
    svg(cls := "w-5 h-5", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"))
    )

  private def componentsIcon: Html[Msg] =
    svg(cls := "w-5 h-5", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M4 5a1 1 0 011-1h4a1 1 0 011 1v7a1 1 0 01-1 1H5a1 1 0 01-1-1V5zM14 5a1 1 0 011-1h4a1 1 0 011 1v7a1 1 0 01-1 1h-4a1 1 0 01-1-1V5zM4 16a1 1 0 011-1h4a1 1 0 011 1v3a1 1 0 01-1 1H5a1 1 0 01-1-1v-3zM14 16a1 1 0 011-1h4a1 1 0 011 1v3a1 1 0 01-1 1h-4a1 1 0 01-1-1v-3z"))
    )

  private def settingsIcon: Html[Msg] =
    svg(cls := "w-5 h-5", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M10.325 4.317c.426-1.756 2.924-1.756 3.35 0a1.724 1.724 0 002.573 1.066c1.543-.94 3.31.826 2.37 2.37a1.724 1.724 0 001.065 2.572c1.756.426 1.756 2.924 0 3.35a1.724 1.724 0 00-1.066 2.573c.94 1.543-.826 3.31-2.37 2.37a1.724 1.724 0 00-2.572 1.065c-.426 1.756-2.924 1.756-3.35 0a1.724 1.724 0 00-2.573-1.066c-1.543.94-3.31-.826-2.37-2.37a1.724 1.724 0 00-1.065-2.572c-1.756-.426-1.756-2.924 0-3.35a1.724 1.724 0 001.066-2.573c-.94-1.543.826-3.31 2.37-2.37.996.608 2.296.07 2.572-1.065z"))
      ,
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M15 12a3 3 0 11-6 0 3 3 0 016 0z"))
    )

  private def scalaLogo: Html[Msg] =
    svg(attribute("viewBox", "0 0 256 416"), cls := "w-full h-full")(
      // First stripe (top)
      path(
        attribute("fill", "#FFFFFF"),
        attribute("d", "M0,288 L0,352 C0,352 256,320 256,224 L256,160 C256,160 0,192 0,288 Z")
      ),
      // Second stripe (middle)
      path(
        attribute("fill", "#FFFFFF"),
        attribute("d", "M0,160 L0,224 C0,224 256,192 256,96 L256,32 C256,32 0,64 0,160 Z")
      ),
      // Third stripe (bottom)
      path(
        attribute("fill", "#FFFFFF"),
        attribute("d", "M0,416 L0,480 C0,480 256,448 256,352 L256,288 C256,288 0,320 0,416 Z")
      )
    )

  def apply(content: Html[Msg], model: Model): Html[Msg] =
    div()(
      sidebar(model.currentPage),
      div(cls := "main-content")(content)
    )
