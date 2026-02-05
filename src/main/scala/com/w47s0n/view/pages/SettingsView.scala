package com.w47s0n.view.pages

import tyrian.Html.*
import tyrian.SVG.*
import tyrian.Html
import com.w47s0n.model.Msg

object SettingsView:

  def apply(): Html[Msg] =
    div()(
      div(cls := "mb-8")(
        h1(cls := "text-3xl font-bold text-base-content mb-2")("Settings"),
        p(cls := "text-base-content/60")("Manage your application preferences")
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
        div(cls := "card bg-base-200 border border-error/30 shadow")(
          div(cls := "card-body")(
            h3(cls := "text-lg font-semibold text-error mb-4")("Danger Zone"),
            div(cls := "flex items-center justify-between")(
              div()(
                div(cls := "font-medium text-base-content")("Delete Account"),
                div(cls := "text-sm text-base-content/60")("Permanently delete your account and all data")
              ),
              button(cls := "btn btn-error btn-outline btn-sm")(
                "Delete Account"
              )
            )
          )
        )
      )
    )

  private def settingsSection(title: String)(content: Html[Msg]*): Html[Msg] =
    div(cls := "card bg-base-200 border border-base-300 shadow")(
      div(cls := "card-body")(
        h3(cls := "text-lg font-semibold text-base-content mb-4")(title),
        div(cls := "space-y-4")(content*)
      )
    )

  private def settingItem(labelText: String, value: String, inputType: String): Html[Msg] =
    div(cls := "flex items-center justify-between")(
      label(cls := "text-base-content/80")(labelText),
      input(
        `type` := inputType,
        attribute("value", value),
        cls := "input input-bordered w-64"
      )
    )

  private def toggleItem(labelText: String, description: String, enabled: Boolean): Html[Msg] =
    div(cls := "flex items-center justify-between")(
      div()(
        div(cls := "font-medium text-base-content")(labelText),
        div(cls := "text-sm text-base-content/60")(description)
      ),
      input(
        `type` := "checkbox",
        cls := "toggle toggle-primary",
        if enabled then checked else attribute("data-unchecked", "")
      )
    )
