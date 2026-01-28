package com.w47s0n.view.pages

import tyrian.Html.*
import tyrian.SVG.*
import tyrian.Html
import com.w47s0n.model.Msg

/**
 * ComponentsGalleryView - DaisyUI components showcase
 *
 * Consolidates component demos from separate pages into one organized gallery.
 * Demonstrates the breadth of DaisyUI components available in the application.
 */
object ComponentsGalleryView:

  def apply(): Html[Msg] =
    div()(
      // Header
      div(cls := "mb-8")(
        h1(cls := "text-3xl font-semibold text-base-content mb-2")(
          "Components Gallery"
        ),
        p(cls := "text-base-content/60")(
          "DaisyUI component library showcase"
        )
      ),

      // Buttons Section
      section("Buttons", buttonsSection),

      // Badges Section
      section("Badges", badgesSection),

      // Alerts Section
      section("Alerts", alertsSection),

      // Cards Section
      section("Cards", cardsSection),

      // Form Elements Section
      section("Form Elements", formsSection),

      // Accordion Section
      section("Accordion", accordionSection)
    )

  private def section(title: String, content: Html[Msg]): Html[Msg] =
    div(cls := "mb-10")(
      h2(cls := "text-xl font-semibold mb-4 text-base-content")(title),
      content
    )

  // Buttons Section
  private def buttonsSection: Html[Msg] =
    div(cls := "space-y-6")(
      div(cls := "card bg-base-200 border border-base-300 shadow")(
        div(cls := "card-body")(
          h3(cls := "card-title text-xl mb-4")("Basic Buttons"),
          div(cls := "flex flex-wrap gap-3")(
            button(cls := "btn btn-primary")("Primary"),
            button(cls := "btn btn-secondary")("Secondary"),
            button(cls := "btn btn-accent")("Accent"),
            button(cls := "btn btn-info")("Info"),
            button(cls := "btn btn-success")("Success"),
            button(cls := "btn btn-warning")("Warning"),
            button(cls := "btn btn-error")("Error")
          )
        )
      ),
      div(cls := "card bg-base-200 border border-base-300 shadow")(
        div(cls := "card-body")(
          h3(cls := "card-title text-xl mb-4")("Button Variants"),
          div(cls := "flex flex-wrap gap-3")(
            button(cls := "btn btn-outline btn-primary")("Outline"),
            button(cls := "btn btn-ghost")("Ghost"),
            button(cls := "btn btn-link")("Link"),
            button(cls := "btn btn-primary loading")("Loading")
          )
        )
      ),
      div(cls := "card bg-base-200 border border-base-300 shadow")(
        div(cls := "card-body")(
          h3(cls := "card-title text-xl mb-4")("Button Sizes"),
          div(cls := "flex flex-wrap items-center gap-3")(
            button(cls := "btn btn-primary btn-xs")("Tiny"),
            button(cls := "btn btn-primary btn-sm")("Small"),
            button(cls := "btn btn-primary")("Normal"),
            button(cls := "btn btn-primary btn-lg")("Large")
          )
        )
      )
    )

  // Badges Section
  private def badgesSection: Html[Msg] =
    div(cls := "space-y-6")(
      div(cls := "card bg-base-200 border border-base-300 shadow")(
        div(cls := "card-body")(
          h3(cls := "card-title text-xl mb-4")("Badge Colors"),
          div(cls := "flex flex-wrap gap-3")(
            span(cls := "badge badge-primary")("Primary"),
            span(cls := "badge badge-secondary")("Secondary"),
            span(cls := "badge badge-accent")("Accent"),
            span(cls := "badge badge-info")("Info"),
            span(cls := "badge badge-success")("Success"),
            span(cls := "badge badge-warning")("Warning"),
            span(cls := "badge badge-error")("Error"),
            span(cls := "badge badge-ghost")("Ghost")
          )
        )
      ),
      div(cls := "card bg-base-200 border border-base-300 shadow")(
        div(cls := "card-body")(
          h3(cls := "card-title text-xl mb-4")("Badge Sizes"),
          div(cls := "flex flex-wrap items-center gap-3")(
            span(cls := "badge badge-primary badge-xs")("Tiny"),
            span(cls := "badge badge-primary badge-sm")("Small"),
            span(cls := "badge badge-primary")("Normal"),
            span(cls := "badge badge-primary badge-lg")("Large")
          )
        )
      )
    )

  // Alerts Section
  private def alertsSection: Html[Msg] =
    div(cls := "space-y-4")(
      div(cls := "alert alert-info")(
        infoIcon,
        span()("This is an informational alert with an icon!")
      ),
      div(cls := "alert alert-success")(
        successIcon,
        span()("Your operation completed successfully!")
      ),
      div(cls := "alert alert-warning")(
        warningIcon,
        span()("Warning: Please review your settings before proceeding.")
      ),
      div(cls := "alert alert-error")(
        errorIcon,
        span()("Error: Something went wrong. Please try again.")
      )
    )

  // Cards Section
  private def cardsSection: Html[Msg] =
    div(cls := "grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6")(
      div(cls := "card bg-base-200 shadow-xl")(
        figure(cls := "px-10 pt-10")(
          div(cls := "w-24 h-24 rounded-full bg-gradient-to-br from-primary to-secondary flex items-center justify-center text-4xl")(
            "🎨"
          )
        ),
        div(cls := "card-body items-center text-center")(
          h2(cls := "card-title")("Design"),
          p()("Beautiful, accessible components"),
          div(cls := "card-actions")(
            button(cls := "btn btn-primary btn-sm")("Learn More")
          )
        )
      ),
      div(cls := "card bg-base-200 shadow-xl")(
        figure(cls := "px-10 pt-10")(
          div(cls := "w-24 h-24 rounded-full bg-gradient-to-br from-secondary to-accent flex items-center justify-center text-4xl")(
            "⚡"
          )
        ),
        div(cls := "card-body items-center text-center")(
          h2(cls := "card-title")("Performance"),
          p()("Lightning-fast rendering"),
          div(cls := "card-actions")(
            button(cls := "btn btn-secondary btn-sm")("Learn More")
          )
        )
      ),
      div(cls := "card bg-base-200 shadow-xl")(
        figure(cls := "px-10 pt-10")(
          div(cls := "w-24 h-24 rounded-full bg-gradient-to-br from-accent to-primary flex items-center justify-center text-4xl")(
            "🎯"
          )
        ),
        div(cls := "card-body items-center text-center")(
          h2(cls := "card-title")("Type Safety"),
          p()("Scala's strong type system"),
          div(cls := "card-actions")(
            button(cls := "btn btn-accent btn-sm")("Learn More")
          )
        )
      )
    )

  // Forms Section
  private def formsSection: Html[Msg] =
    div(cls := "card bg-base-300/50 backdrop-blur-xl border border-base-300 shadow-xl")(
      div(cls := "card-body")(
        div(cls := "grid grid-cols-1 md:grid-cols-2 gap-6")(
          // Inputs
          div()(
            h3(cls := "text-xl font-bold mb-4")("Text Inputs"),
            div(cls := "space-y-3")(
              input(tpe := "text", placeholder := "Default input", cls := "input input-bordered w-full"),
              input(tpe := "text", placeholder := "Primary input", cls := "input input-bordered input-primary w-full"),
              input(tpe := "text", placeholder := "Secondary input", cls := "input input-bordered input-secondary w-full")
            )
          ),
          // Checkboxes & Toggles
          div()(
            h3(cls := "text-xl font-bold mb-4")("Checkboxes & Toggles"),
            div(cls := "space-y-4")(
              div(cls := "form-control")(
                label(cls := "label cursor-pointer gap-3 justify-start")(
                  input(tpe := "checkbox", cls := "checkbox checkbox-primary"),
                  span(cls := "label-text")("Remember me")
                )
              ),
              div(cls := "form-control")(
                label(cls := "label cursor-pointer gap-3 justify-start")(
                  input(tpe := "checkbox", cls := "toggle toggle-primary"),
                  span(cls := "label-text")("Enable notifications")
                )
              ),
              div(cls := "form-control")(
                label(cls := "label cursor-pointer gap-3 justify-start")(
                  input(tpe := "checkbox", cls := "toggle toggle-secondary"),
                  span(cls := "label-text")("Dark mode")
                )
              )
            )
          )
        ),
        div(cls := "mt-6")(
          h3(cls := "text-xl font-bold mb-4")("Select & Textarea"),
          div(cls := "grid grid-cols-1 md:grid-cols-2 gap-4")(
            select(cls := "select select-bordered w-full")(
              option(disabled(true), attribute("selected", "selected"))("Choose an option"),
              option()("Option 1"),
              option()("Option 2"),
              option()("Option 3")
            ),
            textarea(
              cls := "textarea textarea-bordered",
              placeholder := "Enter your message..."
            )()
          )
        )
      )
    )

  // Accordion Section
  private def accordionSection: Html[Msg] =
    div(cls := "space-y-2")(
      div(cls := "collapse collapse-arrow bg-base-200")(
        input(tpe := "radio", name := "components-accordion", checked := true),
        div(cls := "collapse-title text-xl font-medium")("What is DaisyUI?"),
        div(cls := "collapse-content")(
          p()("DaisyUI is a component library for Tailwind CSS that provides beautiful, accessible UI components out of the box.")
        )
      ),
      div(cls := "collapse collapse-arrow bg-base-200")(
        input(tpe := "radio", name := "components-accordion"),
        div(cls := "collapse-title text-xl font-medium")("Why use Tyrian?"),
        div(cls := "collapse-content")(
          p()("Tyrian brings the Elm architecture to Scala.js, providing a functional, type-safe way to build interactive web applications with predictable state management.")
        )
      ),
      div(cls := "collapse collapse-arrow bg-base-200")(
        input(tpe := "radio", name := "components-accordion"),
        div(cls := "collapse-title text-xl font-medium")("What makes Tauri special?"),
        div(cls := "collapse-content")(
          p()("Tauri allows you to build lightweight, secure desktop applications using web technologies, with a Rust-powered backend for maximum performance and security.")
        )
      )
    )

  // SVG Icons
  private def infoIcon: Html[Msg] =
    svg(cls := "w-6 h-6 shrink-0 stroke-current", attribute("fill", "none"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"))
    )

  private def successIcon: Html[Msg] =
    svg(cls := "w-6 h-6 shrink-0 stroke-current", attribute("fill", "none"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"))
    )

  private def warningIcon: Html[Msg] =
    svg(cls := "w-6 h-6 shrink-0 stroke-current", attribute("fill", "none"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z"))
    )

  private def errorIcon: Html[Msg] =
    svg(cls := "w-6 h-6 shrink-0 stroke-current", attribute("fill", "none"), attribute("viewBox", "0 0 24 24"))(
      path(attribute("stroke-linecap", "round"), attribute("stroke-linejoin", "round"), attribute("stroke-width", "2"),
        attribute("d", "M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"))
    )
