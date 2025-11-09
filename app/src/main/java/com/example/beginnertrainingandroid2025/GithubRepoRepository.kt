package com.example.beginnertrainingandroid2025

interface GithubRepoRepository {
    suspend fun getRepoList(): List<Repo>

    suspend fun saveAsBookmark(repo: Repo)

    suspend fun saveAsUnBookmark(repo: Repo)

    suspend fun getBookmarkedRepoList(): List<Repo>
}