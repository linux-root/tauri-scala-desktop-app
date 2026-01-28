package com.w47s0n.util

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import scala.scalajs.js.JSConverters.*
import scala.concurrent.Future

/** Scala.js facades for Tauri APIs */
object TauriAPI {

  /** Dialog file filters */
  @js.native
  trait DialogFilter extends js.Object {
    val name: String
    val extensions: js.Array[String]
  }

  object DialogFilter {
    def apply(name: String, extensions: String*): DialogFilter = {
      js.Dynamic.literal(
        name = name,
        extensions = js.Array(extensions*)
      ).asInstanceOf[DialogFilter]
    }
  }

  /** Options for opening a file dialog */
  @js.native
  trait OpenDialogOptions extends js.Object {
    val title: js.UndefOr[String]
    val filters: js.UndefOr[js.Array[DialogFilter]]
    val multiple: js.UndefOr[Boolean]
    val directory: js.UndefOr[Boolean]
  }

  object OpenDialogOptions {
    def apply(
      title: Option[String] = None,
      filters: Option[Seq[DialogFilter]] = None,
      multiple: Boolean = false,
      directory: Boolean = false
    ): OpenDialogOptions = {
      js.Dynamic.literal(
        title = title.orUndefined,
        filters = filters.map(f => js.Array(f*)).orUndefined,
        multiple = multiple,
        directory = directory
      ).asInstanceOf[OpenDialogOptions]
    }
  }

  /** Tauri Dialog API */
  @js.native
  @JSImport("@tauri-apps/plugin-dialog", "open")
  def openDialog(options: OpenDialogOptions): js.Promise[js.UndefOr[String | js.Array[String]]] = js.native

  /** Tauri Filesystem API */
  @js.native
  @JSImport("@tauri-apps/plugin-fs", "readTextFile")
  def readTextFile(path: String): js.Promise[String] = js.native

  /** Helper to convert JS Promise to Scala Future */
  implicit class PromiseOps[T](promise: js.Promise[T]) {
    def toScalaFuture: Future[T] = {
      import scala.scalajs.js.Thenable.Implicits._
      promise.toFuture
    }
  }
}
