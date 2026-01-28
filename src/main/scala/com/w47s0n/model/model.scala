package com.w47s0n.model

import com.w47s0n.page.*

enum Msg {
  case NoOp
  case LogMessage(msg: String)
  case NavigateTo(page: Page)
  case ToggleDarkMode
  case UpdateUsername(username: String)
  case UpdatePassword(password: String)
  case SubmitLogin
  case LoginSuccess
  case LoginError(message: String)
  case Logout
  case OpenFileDialog
  case FileSelected(path: String)
  case FileContentLoaded(content: String)
  case FileError(message: String)
  case FetchGitHubRepos
  case GitHubReposLoaded(repos: List[GitHubRepo])
  case GitHubReposError(message: String)
}

case class LoginForm(
  username: String = "",
  password: String = "",
  isLoading: Boolean = false,
  error: Option[String] = None
)

case class FileUploadState(
  selectedFilePath: Option[String] = None,
  fileContent: Option[String] = None,
  isLoading: Boolean = false,
  error: Option[String] = None
)

case class Model(
  currentPage: Page,
  isDarkMode: Boolean,
  isAuthenticated: Boolean = false,
  loginForm: LoginForm = LoginForm(),
  fileUploadState: FileUploadState = FileUploadState(),
  gitHubReposState: GitHubReposState = GitHubReposState()
):
  def toggleDarkMode: Model = copy(isDarkMode = !isDarkMode)

object Model {
  val init: Model = Model(Page.Login, isDarkMode = false, isAuthenticated = false)
}
