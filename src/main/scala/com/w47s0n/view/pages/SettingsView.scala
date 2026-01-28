package com.w47s0n.view.pages

import tyrian.Html.*
import tyrian.SVG.*
import tyrian.Html
import com.w47s0n.model.Msg

object SettingsView:

  def apply(): Html[Msg] =
    div()(
      div(cls := "mb-8")(
        h1(cls := "text-3xl font-bold text-gray-100 mb-2")("Settings"),
        p(cls := "text-gray-400")("Manage your application preferences")
      ),
      div(cls := "space-y-6")(
        settingsSection("Profile Settings")(
          settingItem("Display Name", "User Name", "text"),
          settingItem("Email", "user@example.com", "email")
        ),
        settingsSection("Appearance")(
          toggleItem("Dark Mode", "Enable dark theme across the application", enabled = true),
          toggleItem("Compact View", "Use smaller spacing and fonts", enabled = false)
        ),
        settingsSection("Notifications")(
          toggleItem("Email Notifications", "Receive updates via email", enabled = true),
          toggleItem("Push Notifications", "Receive browser notifications", enabled = false),
          toggleItem("Weekly Digest", "Get weekly summary reports", enabled = true)
        ),
        div(cls := "card border border-red-500/30")(
          h3(cls := "text-lg font-semibold text-red-400 mb-4")("Danger Zone"),
          div(cls := "flex items-center justify-between")(
            div()(
              div(cls := "font-medium text-gray-200")("Delete Account"),
              div(cls := "text-sm text-gray-400")("Permanently delete your account and all data")
            ),
            button(cls := "px-4 py-2 bg-red-500/20 text-red-400 rounded-lg hover:bg-red-500/30 transition-colors")(
              "Delete Account"
            )
          )
        )
      )
    )

  private def settingsSection(title: String)(content: Html[Msg]*): Html[Msg] =
    div(cls := "card")(
      h3(cls := "text-lg font-semibold text-gray-100 mb-4")(title),
      div(cls := "space-y-4")(content*)
    )

  private def settingItem(labelText: String, value: String, inputType: String): Html[Msg] =
    div(cls := "flex items-center justify-between")(
      label(cls := "text-gray-300")(labelText),
      input(
        `type` := inputType,
        attribute("value", value),
        cls := "bg-gray-800 border border-gray-700 rounded-lg px-4 py-2 text-gray-200 focus:outline-none focus:border-blue-500 w-64"
      )
    )

  private def toggleItem(labelText: String, description: String, enabled: Boolean): Html[Msg] =
    div(cls := "flex items-center justify-between")(
      div()(
        div(cls := "font-medium text-gray-200")(labelText),
        div(cls := "text-sm text-gray-400")(description)
      ),
      div(cls := s"w-12 h-6 rounded-full cursor-pointer transition-colors ${if enabled then "bg-blue-500" else "bg-gray-600"}")(
        div(cls := s"w-5 h-5 rounded-full bg-white shadow-md transform transition-transform ${if enabled then "translate-x-6" else "translate-x-0.5"} mt-0.5")()
      )
    )
