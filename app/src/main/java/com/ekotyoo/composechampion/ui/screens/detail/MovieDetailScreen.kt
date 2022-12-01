package com.ekotyoo.composechampion.ui.screens.detail

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ekotyoo.composechampion.common.components.BackButton
import com.ekotyoo.composechampion.ui.screens.detail.components.CastList
import com.ekotyoo.composechampion.ui.screens.detail.components.FavoriteButton
import com.ekotyoo.composechampion.ui.screens.detail.components.MovieHeader
import com.ekotyoo.composechampion.ui.screens.detail.components.MovieOverview
import com.ekotyoo.composechampion.ui.screens.detail.model.MovieDetailUiModel
import com.ekotyoo.composechampion.ui.theme.ComposeChampionTheme

@Composable
fun MovieDetailScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: MovieDetailViewModel,
    onNavigateBack: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    if (state.data != null) {
        MovieDetailScreen(
            movieData = state.data!!,
            onFavoriteClick = viewModel::addMovieToFavorite,
            onNavigateBack = onNavigateBack,
        )
    } else {
        LaunchedEffect(true) {
            snackbarHostState.showSnackbar(message = "Failed to load movie data")
            onNavigateBack()
        }
    }

    state.message?.let {
        LaunchedEffect(it) {
            snackbarHostState.showSnackbar(message = it)
            viewModel.messageShown()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    movieData: MovieDetailUiModel,
    onFavoriteClick: (String, Boolean) -> Unit,
    onNavigateBack: () -> Unit,
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val boxSize = maxHeight * 3 / 5

        val gradient = Brush.verticalGradient(
            colorStops = arrayOf(
                0.0f to Color.Transparent,
                0.15f to MaterialTheme.colorScheme.background,
            ),
        )

        AsyncImage(
            model = movieData.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(boxSize)
                .align(Alignment.TopCenter)
        )
        Box(
            modifier = Modifier
                .background(gradient)
                .fillMaxWidth()
                .height(boxSize)
                .align(Alignment.BottomCenter),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                MovieHeader(
                    title = movieData.title,
                    rating = movieData.rating,
                    year = movieData.year,
                    genre = movieData.genre.joinToString(", ")
                )
                Spacer(modifier = Modifier.height(16.dp))
                MovieOverview(overview = movieData.overview)
                Spacer(modifier = Modifier.height(16.dp))
                CastList(casts = movieData.cast)
                Spacer(modifier = Modifier.height(32.dp))
                AnimatedContent(
                    targetState = movieData.isFavorite,
                    modifier = Modifier.align(Alignment.End),
                    transitionSpec = {
                        ContentTransform(
                            targetContentEnter = fadeIn(),
                            initialContentExit = fadeOut()
                        )
                    }
                ) { isFavorite ->
                    FavoriteButton(
                        onClick = { onFavoriteClick(movieData.id, movieData.isFavorite) },
                        isFavorite = isFavorite,
                    )
                }
            }
        }
        BackButton(onNavigateBack = onNavigateBack)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovieDetailScreenPreview() {
    ComposeChampionTheme {
    }
}