package com.w47s0n.view.pages

import tyrian.Html.*
import tyrian.SVG.*
import tyrian.*
import com.w47s0n.model.*

object FileUploadView:

  def apply(model: Model): Html[Msg] =
    div(cls := "max-w-4xl mx-auto")(
      div(cls := "mb-8")(
        h1(cls := "text-3xl font-semibold text-base-content mb-2")(
          "File Upload"
        ),
        p(cls := "text-base-content/60")(
          "Native desktop file dialogs with Tauri integration"
        )
      ),
      div(cls := "card bg-base-200 shadow-2xl border border-base-300")(
        div(cls := "card-body")(
          div(cls := "flex justify-center mb-6")(
            button(
              cls := "btn btn-primary btn-lg gap-2",
              onClick(Msg.OpenFileDialog),
              disabled(model.fileUploadState.isLoading)
            )(
              if model.fileUploadState.isLoading then
                span(cls := "loading loading-spinner")()
              else
                uploadIcon,
              text(if model.fileUploadState.isLoading then "Loading..." else "Choose Text File")
            )
          ),
          model.fileUploadState.selectedFilePath.map { path =>
            div(cls := "alert alert-info shadow-lg mb-6")(
              infoIcon,
              div()(
                div(cls := "font-bold")("File Selected"),
                div(cls := "text-sm")(path)
              )
            )
          }.getOrElse(emptyNode),
          model.fileUploadState.error.map { errorMsg =>
            div(cls := "alert alert-error shadow-lg mb-6")(
              errorIcon,
              span(errorMsg)
            )
          }.getOrElse(emptyNode),
          model.fileUploadState.fileContent.map { content =>
            div(cls := "mt-6")(
              div(cls := "flex items-center justify-between mb-4")(
                h3(cls := "text-2xl font-bold")("File Contents"),
                div(cls := "badge badge-primary badge-lg")(
                  s"${content.length} characters"
                )
              ),
              div(cls := "mockup-code bg-base-300 shadow-xl")(
                pre(attribute("data-prefix", "$"))(
                  code(cls := "text-sm")(content)
                )
              )
            )
          }.getOrElse(emptyNode)
        )
      ),
      div(cls := "grid grid-cols-1 md:grid-cols-3 gap-4 mt-8")(
        capabilityCard(
          "Native Dialogs",
          "Direct OS file system integration",
          "📁"
        ),
        capabilityCard(
          "Fast & Secure",
          "Rust-powered backend for safety",
          "🔒"
        ),
        capabilityCard(
          "Cross-Platform",
          "Works seamlessly on all OS",
          "💻"
        )
      )
    )

  private def capabilityCard(title: String, description: String, emoji: String): Html[Msg] =
    div(cls := "card bg-base-200 border border-base-300 shadow")(
      div(cls := "card-body items-center text-center")(
        div(cls := "text-3xl mb-3")(emoji),
        h3(cls := "font-semibold text-base-content text-sm")(title),
        p(cls := "text-base-content/60 text-xs")(description)
      )
    )

  private def uploadIcon: Html[Msg] =
    svg(cls := "w-6 h-6", attribute("fill", "none"), attribute("stroke", "currentColor"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M7 16a4 4 0 01-.88-7.903A5 5 0 1115.9 6L16 6a5 5 0 011 9.9M15 13l-3-3m0 0l-3 3m3-3v12"))
    )

  private def infoIcon: Html[Msg] =
    svg(cls := "w-6 h-6 shrink-0 stroke-current", attribute("fill", "none"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"))
    )

  private def errorIcon: Html[Msg] =
    svg(cls := "w-6 h-6 shrink-0 stroke-current", attribute("fill", "none"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"))
    )

  private val emptyNode: Html[Msg] = div()
