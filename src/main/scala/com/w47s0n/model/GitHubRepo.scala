package com.w47s0n.model

case class GitHubRepo(
  name: String,
  full_name: String,
  description: Option[String],
  stargazers_count: Int,
  forks_count: Int,
  open_issues_count: Int,
  language: Option[String],
  html_url: String
)

object GitHubRepo:
  extension (repo: GitHubRepo)
    def fullName: String = repo.full_name
    def stars: Int = repo.stargazers_count
    def forks: Int = repo.forks_count
    def openIssues: Int = repo.open_issues_count
    def htmlUrl: String = repo.html_url

case class GitHubReposState(
  repos: List[GitHubRepo] = List.empty,
  isLoading: Boolean = false,
  error: Option[String] = None
)
