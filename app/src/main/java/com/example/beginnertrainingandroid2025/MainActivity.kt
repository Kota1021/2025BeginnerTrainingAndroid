package com.example.beginnertrainingandroid2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.beginnertrainingandroid2025.ui.theme.BeginnerTrainingAndroid2025Theme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var repos = remember { mutableStateListOf<Repo>() }
            BeginnerTrainingAndroid2025Theme {
                    LaunchedEffect(Unit) {
                        val result: List<Repo> = httpClient.get("https://api.github.com/orgs/mixigroup/repos").body()
                        repos.addAll(result)
                    }
                HomeScreen(
                    repos = repos,
                )
            }
        }
    }
}

@Composable
fun RepoListItem(
    repo: Repo,
    modifier: Modifier = Modifier,
) {
    Column {
        Text(repo.name)
        repo.description?.let { Text(it) }
        Row {
            Icon(
                imageVector = Icons.Outlined.Star,
                tint = Color.LightGray,
                contentDescription = null,
            )
            Text("${repo.stars}")
        }
    }
}

@Serializable
data class Repo(
    val id: Int,
    val name: String,
    val description: String? = null,
    @SerialName("stargazers_count") val stars: Int,
)

class RepoPreviewParameterProvider: PreviewParameterProvider<Repo> {
    override val values = sequenceOf(
        Repo(
            id = 1,
            name = "hoge",
            stars = 123,
        ),
        Repo(
            id = 2,
            name = "foo",
            description = "This is awesome repository.",
            stars = 1234,
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun RepoListItemPreview(
    @PreviewParameter(RepoPreviewParameterProvider::class) repo: Repo,
) {
    RepoListItem(repo = repo)
}

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

