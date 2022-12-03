package com.ekotyoo.composechampion.ui.screens.about.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ekotyoo.composechampion.R
import com.ekotyoo.composechampion.ui.screens.home.components.MovieCard
import com.ekotyoo.composechampion.ui.screens.home.model.MovieListItemUiModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    movies: List<MovieListItemUiModel>,
    onItemClick: (String) -> Unit,
    onFavorite: (String, Boolean) -> Unit,
    listState: LazyListState = rememberLazyListState(),
    header: @Composable (() -> Unit)? = null,
) {
    if (movies.isNotEmpty()) {
        LazyColumn(modifier = modifier,
            state = listState,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            if (header != null) item { header() }
            items(movies, key = { it.id }) {
                MovieCard(
                    title = it.title,
                    year = it.year,
                    rating = it.rating,
                    image = it.image,
                    genres = it.genre,
                    onClick = { onItemClick(it.id) },
                    isFavorite = it.isFavorite,
                    onFavorite = { onFavorite(it.id, it.isFavorite) },
                    modifier = Modifier.animateItemPlacement(tween(200))
                )
            }
        }
    } else {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(id = R.string.desc_favorite_movie_empty),
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f),
            )
        }
    }
}