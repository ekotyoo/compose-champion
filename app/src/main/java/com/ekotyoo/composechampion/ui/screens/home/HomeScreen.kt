package com.ekotyoo.composechampion.ui.screens.home

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ekotyoo.composechampion.R
import com.ekotyoo.composechampion.common.UiState
import com.ekotyoo.composechampion.ui.screens.home.components.SearchHeader
import com.ekotyoo.composechampion.ui.screens.home.components.MovieCard
import com.ekotyoo.composechampion.ui.screens.home.model.MovieListItemUiModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onNavigateToAboutScreen: () -> Unit,
    onNavigateToDetailScreen: (String) -> Unit,
) {

    val searchQuery by viewModel.searchQuery.collectAsState()

    viewModel.uiState.collectAsState().value.let { state ->
        when (state) {
            is UiState.Error -> {}
            is UiState.Loading -> {}
            is UiState.Success -> {
                HomeScreen(
                    searchQuery = searchQuery,
                    modifier = modifier,
                    movies = state.data,
                    onNavigateToAboutScreen = onNavigateToAboutScreen,
                    onNavigateToDetailScreen = onNavigateToDetailScreen,
                    onSearchValueChange = viewModel::onQueryChanged,
                    onFavoriteClick = viewModel::addMovieToFavorite
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    movies: List<MovieListItemUiModel>,
    onNavigateToAboutScreen: () -> Unit,
    onNavigateToDetailScreen: (String) -> Unit,
    onSearchValueChange: (String) -> Unit,
    searchQuery: String,
    onFavoriteClick: (String, Boolean) -> Unit,
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val showScrollToTop = remember {
        derivedStateOf { listState.firstVisibleItemIndex > 1 }
    }

    Box(modifier = modifier) {
        LazyColumn(modifier = modifier,
            state = listState,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            item {
                SearchHeader(
                    onSearchValueChange = onSearchValueChange,
                    searchValue = searchQuery,
                    onNavigateToAboutScreen = onNavigateToAboutScreen,
                )
            }
            if (movies.isEmpty()) {
                item {
                    Text(
                        text = stringResource(id = R.string.desc_movie_not_found),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            } else {
                items(movies, key = { it.id }) {
                    MovieCard(
                        title = it.title,
                        year = it.year,
                        rating = it.rating,
                        image = it.image,
                        genres = it.genre,
                        onClick = { onNavigateToDetailScreen(it.id) },
                        isFavorite = it.isFavorite,
                        onFavorite = {
                            onFavoriteClick(it.id, it.isFavorite)
                        },
                        modifier = Modifier.animateItemPlacement(tween(200))
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = showScrollToTop.value,
            enter = fadeIn() + scaleIn() + slideInVertically(initialOffsetY = { it / 2 }),
            exit = fadeOut() + scaleOut() + slideOutVertically(targetOffsetY = { it / 2 }),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)) {
            ScrollToTopButton(onClick = { scope.launch { listState.animateScrollToItem(0) } })
        }
    }
}

@Composable
fun ScrollToTopButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(onClick = onClick,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.tertiaryContainer) {
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowUp,
            tint = MaterialTheme.colorScheme.onTertiaryContainer,
            contentDescription = stringResource(id = R.string.desc_scroll_top_button),
        )
    }
}