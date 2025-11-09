package com.example.beginnertrainingandroid2025

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoRemoteDataSource {
    // withContextを使ってIO処理用のスレッドで実行させる
    suspend fun getRepoList(): List<Repo> = withContext(Dispatchers.IO) {
        httpClient.get("https://api.github.com/orgs/mixigroup/repos").body()
    }
}