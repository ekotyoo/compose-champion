package com.ekotyoo.composechampion.ui.screens.home

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ekotyoo.composechampion.R
import com.ekotyoo.composechampion.ui.screens.home.components.Header
import com.ekotyoo.composechampion.ui.screens.home.components.MovieCard
import com.ekotyoo.composechampion.ui.screens.home.model.MovieListItemUiModel
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToAboutScreen: () -> Unit,
    onNavigateToDetailScreen: (String) -> Unit,
) {
    val movies: List<MovieListItemUiModel> = remember {
        List(10) {
            MovieListItemUiModel(
                id = it.toString(),
                title = "Title $it",
                rating = (Random.nextFloat() * 10),
                genre = listOf("Genre 1", "Genre 2", "Genre 3"),
                year = Random.nextInt(2002, 2022),
                image = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg"
            )
        }
    }

    HomeScreen(
        modifier = modifier,
        movies = movies,
        onNavigateToAboutScreen = onNavigateToAboutScreen,
        onNavigateToDetailScreen = onNavigateToDetailScreen,
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    movies: List<MovieListItemUiModel>,
    onNavigateToAboutScreen: () -> Unit,
    onNavigateToDetailScreen: (String) -> Unit,
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
                Header(onSearchValueChange = {},
                    searchValue = "",
                    onNavigateToAboutScreen = onNavigateToAboutScreen)
            }
            items(movies, key = { it.id }) {
                MovieCard(
                    title = it.title,
                    year = it.year,
                    rating = it.rating,
                    image = it.image,
                    genres = it.genre,
                    onClick = { onNavigateToDetailScreen(it.id) }
                )
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