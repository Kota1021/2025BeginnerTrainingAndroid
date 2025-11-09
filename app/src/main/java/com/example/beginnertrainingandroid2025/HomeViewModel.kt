package com.example.beginnertrainingandroid2025
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val  repository: RepoRepository,
): ViewModel() {
    var uiState = MutableStateFlow(
        HomeUiState(
            items = emptyList(),
            bookmarkedRepos = emptySet()
        )
    )
        private set

    fun onLaunched() {
        viewModelScope.launch {
            uiState.update {
                it.copy(
                    items = repository.getRepoList(),
                    bookmarkedRepos = repository.getBookmarkedRepoList().toSet(),
                    )
            }
        }
    }

    fun onBookmarkIconClick(repo: Repo) {
        viewModelScope.launch {
            uiState.update {
                val bookmarkedRepos = if (repo in uiState.value.bookmarkedRepos) {
                    it.bookmarkedRepos - repo
                } else {
                    it.bookmarkedRepos + repo
                }

                it.copy(bookmarkedRepos = repository.getBookmarkedRepoList().toSet())
            }
        }
    }
    companion object {
        val Factory =
            object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                HomeViewModel(
                    repository = RepoRepository(
                        remoteDataSource = RepoRemoteDataSource(),
                        localDataSource = LocalDataSourceFactory.createRepoLocalDataSource(),
                    ),
                ) as T
        }
    }
}