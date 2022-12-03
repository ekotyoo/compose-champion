package com.ekotyoo.composechampion.ui.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ekotyoo.composechampion.R
import com.ekotyoo.composechampion.common.components.BackButton
import com.ekotyoo.composechampion.ui.screens.about.components.CoverImage
import com.ekotyoo.composechampion.ui.screens.about.components.MovieList
import com.ekotyoo.composechampion.ui.screens.about.components.UserInfo

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    viewModel: AboutViewModel,
    snackbarHostState: SnackbarHostState,
    onNavigateBack: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    state.message?.let {
        LaunchedEffect(it) {
            snackbarHostState.showSnackbar(message = it)
            viewModel.messageShown()
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        CoverImage(image = stringResource(id = R.string.url_profile_cover_picture))
        AsyncImage(
            model = stringResource(id = R.string.url_profile_picture),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .offset(y = (200 - 60).dp)
                .padding(start = 16.dp)
                .border(
                    width = 4.dp,
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.background,
                )
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .size(120.dp)
        )
        Column(
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height((16 + 60).dp))
            UserInfo(
                modifier = Modifier
                    .padding(start = 16.dp),
                name = stringResource(id = R.string.label_profile_email),
                email = stringResource(id = R.string.label_profile_name)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.label_favorite_movies),
                style = MaterialTheme.typography.titleMedium,
            )
            MovieList(
                modifier = Modifier
                    .fillMaxHeight(),
                movies = state.data,
                onItemClick = onNavigateToDetail,
                onFavorite = viewModel::addMovieToFavorite,
            )
        }
        BackButton(onNavigateBack = onNavigateBack)
    }
}