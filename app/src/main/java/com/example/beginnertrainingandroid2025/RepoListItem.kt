package com.example.beginnertrainingandroid2025

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun RepoListItem(
    repo: Repo,
    isBookmarked: Boolean,
    onBookmarkIconClick: (Repo) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.weight(1f)
    ) {
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
        IconButton(onClick = { onBookmarkIconClick(repo) }) {
            val icon = if (isBookmarked) { R.drawable.bookmark_filled } else { R.drawable.bookmark }
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
            )
        }
    }
}

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
    RepoListItem(repo = repo, onBookmarkIconClick = { }, isBookmarked = Random.nextBoolean())
}