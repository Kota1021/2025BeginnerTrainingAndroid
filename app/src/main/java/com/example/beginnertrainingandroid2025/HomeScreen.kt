package com.example.beginnertrainingandroid2025

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    repos: List<Repo>,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("ホーム")
                }
             )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            ) {
                items(
                    items = repos,
                    key = { it.id },
                ) {
                    RepoListItem(repo = it)
                }
            }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview(
) {
    val repos = List(1000) {
        Repo(
            id = it,
            name = "repo$it",
            description = if (it.mod(2) == 0) "This is awesome repository" else null,
            stars = Random.nextInt(1000),
        )
    }
    HomeScreen(repos = repos)
}

// HTTPクライアントオブジェクトの生成コストは低くはないので、インスタンスを使いまわせるようグローバル空間で生成しておく
val httpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        // 不要なJSONは無視したいので、ignoreUnknownKeysをtrueにする
        json(json = Json { ignoreUnknownKeys = true })
    }
}

