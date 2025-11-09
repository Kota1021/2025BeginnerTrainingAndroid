package com.example.beginnertrainingandroid2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.beginnertrainingandroid2025.ui.theme.BeginnerTrainingAndroid2025Theme
import io.ktor.client.call.body
import io.ktor.client.request.get

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

