package com.w47s0n.view.pages

import tyrian.Html
import com.w47s0n.model.Msg
import tyrian.Html.*
import tyrian.Html.{attribute => attr}

object AccordionView:
  def apply(): Html[Msg] =
    div(cls := "space-y-2")(
      // Accordion Item 1
      div(cls := "collapse collapse-arrow bg-base-200")(
        input(`type` := "checkbox", id := "accordion-1", name := "my-accordion"),
        label(
          cls := "collapse-title text-xl font-medium",
          attr("for", "accordion-1")
        )(
          "What is DaisyUI?"
        ),
        div(cls := "collapse-content")(
          p(cls := "mb-2")(
            "DaisyUI is a free and open-source component library built on top of Tailwind CSS. It provides pre-designed, accessible components that are easy to use and customize."
          ),
          p()(
            text("Check out this guide to learn how to "),
            a(
              href := "https://daisyui.com/docs/install/",
              cls  := "link link-primary"
            )("get started"),
            text(" and start building beautiful UIs with DaisyUI components.")
          )
        )
      ),

      // Accordion Item 2
      div(cls := "collapse collapse-arrow bg-base-200")(
        input(`type` := "checkbox", id := "accordion-2", name := "my-accordion"),
        label(
          cls := "collapse-title text-xl font-medium",
          attr("for", "accordion-2")
        )(
          "What are the benefits of DaisyUI?"
        ),
        div(cls := "collapse-content")(
          p(cls := "mb-2")(
            "DaisyUI provides semantic component class names, built-in themes, and no JavaScript dependencies. Everything works with pure CSS."
          ),
          p()(
            text("Unlike other libraries, DaisyUI is "),
            a(
              href := "https://daisyui.com/docs/themes/",
              cls  := "link link-primary"
            )("highly themeable"),
            text(" and works perfectly for desktop applications built with Tauri.")
          )
        )
      ),

      // Accordion Item 3
      div(cls := "collapse collapse-arrow bg-base-200")(
        input(`type` := "checkbox", id := "accordion-3", name := "my-accordion"),
        label(
          cls := "collapse-title text-xl font-medium",
          attr("for", "accordion-3")
        )(
          "How does DaisyUI compare to other UI libraries?"
        ),
        div(cls := "collapse-content")(
          p(cls := "mb-2")(
            "DaisyUI is lighter and faster than JavaScript-based component libraries. It uses pure CSS with checkboxes and radio buttons for interactivity."
          ),
          p(cls := "mb-2")(
            "Perfect for Scala.js applications where you want to minimize JavaScript dependencies and keep your bundle size small."
          ),
          p(cls := "mb-2")("Learn more about these technologies:"),
          ul(cls := "list-disc pl-5 space-y-1")(
            li(
              a(
                href := "https://daisyui.com/",
                cls  := "link link-primary"
              )("DaisyUI Documentation")
            ),
            li(
              a(
                href := "https://tailwindcss.com/",
                cls  := "link link-primary"
              )("Tailwind CSS")
            )
          )
        )
      )
    )
