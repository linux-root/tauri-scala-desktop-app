package com.w47s0n.view.pages

import tyrian.Html.*
import tyrian.SVG.*
import tyrian.Html
import com.w47s0n.model.{Msg, Model}

object LoginView:

  def apply(model: Model): Html[Msg] =
    div(cls := "min-h-screen flex items-center justify-center bg-gradient-to-br from-base-100 via-base-100 to-base-200")(
      div(cls := "w-full max-w-md px-6")(
        div(cls := "card bg-base-200 shadow-xl border border-base-300 overflow-hidden")(
          div(cls := "h-1 bg-gradient-to-r from-primary via-secondary to-accent")(),
          div(cls := "card-body")(
            div(cls := "text-center mb-8")(
              div(cls := "inline-flex items-center justify-center w-16 h-16 rounded-xl bg-primary p-3 mb-4")(
                scalaLogo
              ),
              h1(cls := "text-2xl font-semibold text-base-content mb-2")("Sign In"),
              p(cls := "text-base-content/60 text-sm")("Enter your credentials to continue")
            ),
            model.loginForm.error match {
              case Some(error) =>
                div(cls := "alert alert-error mb-6")(
                  svg(cls := "w-5 h-5 shrink-0 stroke-current", attribute("fill", "none"), attribute("viewBox", "0 0 24 24"))(
                    path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
                      attribute("d", "M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"))
                  ),
                  span(cls := "text-sm")(error)
                )
              case None => text("")
            },
            div(cls := "alert alert-info mb-6")(
              svg(cls := "w-5 h-5 shrink-0 stroke-current", attribute("fill", "none"), attribute("viewBox", "0 0 24 24"))(
                path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
                  attribute("d", "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"))
              ),
              div()(
                div(cls := "text-sm font-medium")("Demo Credentials"),
                div(cls := "text-xs mt-1 space-y-0.5")(
                  div()(text("Username: "), span(cls := "font-mono font-semibold")("scala")),
                  div()(text("Password: "), span(cls := "font-mono font-semibold")("lover"))
                )
              )
            ),
            form()(
              div(cls := "form-control mb-4")(
                label(cls := "label", attribute("for", "username"))(
                  span(cls := "label-text font-medium")("Username")
                ),
                input(
                  cls := "input input-bordered w-full",
                  attribute("type", "text"),
                  attribute("id", "username"),
                  attribute("placeholder", "Enter username"),
                  attribute("value", model.loginForm.username),
                  attribute("autocomplete", "username"),
                  disabled(model.loginForm.isLoading),
                  onInput(s => Msg.UpdateUsername(s))
                )
              ),
              div(cls := "form-control mb-6")(
                label(cls := "label", attribute("for", "password"))(
                  span(cls := "label-text font-medium")("Password")
                ),
                input(
                  cls := "input input-bordered w-full",
                  attribute("type", "password"),
                  attribute("id", "password"),
                  attribute("placeholder", "Enter password"),
                  attribute("value", model.loginForm.password),
                  attribute("autocomplete", "current-password"),
                  disabled(model.loginForm.isLoading),
                  onInput(s => Msg.UpdatePassword(s))
                )
              ),
              button(
                cls := "btn btn-primary w-full",
                tpe := "button",
                onClick(Msg.SubmitLogin),
                disabled(model.loginForm.isLoading)
              )(
                if model.loginForm.isLoading then
                  List(
                    span(cls := "loading loading-spinner")(),
                    text("Signing in...")
                  )
                else
                  List(text("Sign In"))
              )
            ),
            div(cls := "divider mt-6 mb-4")(),
            div(cls := "text-center")(
              p(cls := "text-base-content/50 text-xs")("Tyrian Desktop Demo Application")
            )
          )
        )
      )
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
