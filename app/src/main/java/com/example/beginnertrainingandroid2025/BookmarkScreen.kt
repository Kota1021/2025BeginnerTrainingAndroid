package com.example.beginnertrainingandroid2025

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onLaunched()
    }
    BookmarkScreen(
        uiState = uiState,
        modifier = modifier,
        onBookmarkIconClick = viewModel::onBookmarkIconClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookmarkScreen(
    modifier: Modifier,
    uiState: HomeUiState,
    onBookmarkIconClick: (Repo) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("ブックマーク")
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
        ) {
            items(
                items = uiState.bookmarkedRepos.toList(),
                key = { it.id },
            ) {
                RepoListItem(
                    repo = it,
                    isBookmarked = uiState.bookmarkedRepos.contains(it),
                    onBookmarkIconClick = onBookmarkIconClick
                )
            }
        }
    }
}
