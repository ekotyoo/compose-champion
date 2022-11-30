package com.ekotyoo.composechampion.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ekotyoo.composechampion.R
import com.ekotyoo.composechampion.ui.screens.detail.components.CastList
import com.ekotyoo.composechampion.ui.screens.detail.components.MovieHeader
import com.ekotyoo.composechampion.ui.screens.detail.components.MovieOverview
import com.ekotyoo.composechampion.ui.screens.detail.model.CastUiModel
import com.ekotyoo.composechampion.ui.theme.ComposeChampionTheme

@Composable
fun MovieDetailScreen(
    movieId: String,
    modifier: Modifier = Modifier,
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
            model = "https://m.media-amazon.com/images/M/MV5BMWMwMGQzZTItY2JlNC00OWZiLWIyMDctNDk2ZDQ2YjRjMWQ0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",
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
                    title = "The Godfather: Part II",
                    rating = 9.0f,
                    year = 1974,
                    genre = "Crime, Drama"
                )
                Spacer(modifier = Modifier.height(16.dp))
                MovieOverview(overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                Spacer(modifier = Modifier.height(16.dp))
                CastList(casts = List(4) {
                    CastUiModel(
                        name = "Name - $it",
                        image = "https://m.media-amazon.com/images/M/MV5BMWMwMGQzZTItY2JlNC00OWZiLWIyMDctNDk2ZDQ2YjRjMWQ0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg",
                        playedAs = "Name - $it"
                    )
                })
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Add to Favorite")
                }
            }
        }
        IconButton(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = MaterialTheme.colorScheme.surfaceVariant),
            onClick = {},
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = stringResource(id = R.string.desc_back_button),
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovieDetailScreenPreview() {
    ComposeChampionTheme {
        MovieDetailScreen(movieId = "")
    }
}