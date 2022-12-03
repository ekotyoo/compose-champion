package com.ekotyoo.composechampion.ui.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ekotyoo.composechampion.R
import com.ekotyoo.composechampion.ui.theme.ComposeChampionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onFavorite: () -> Unit = {},
    title: String,
    year: Int,
    genres: List<String> = emptyList(),
    rating: Float,
    image: String,
    isFavorite: Boolean = false,
) {
    val genresText = genres.joinToString(", ")
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 80.dp, height = 100.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "$year | $genresText",
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 2, overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.fillMaxHeight())
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        String.format("%.1f", rating),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    )
                }
            }
            IconButton(
                onClick = onFavorite,
                modifier = Modifier
                    .align(Alignment.CenterVertically),
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.desc_favorite_button),
                )
            }
        }
    }
}

@Preview(heightDp = 150, widthDp = 400, showBackground = true)
@Composable
fun MovieCardPreview() {
    ComposeChampionTheme {
        MovieCard(
            title = "The Batman",
            year = 2022,
            rating = 4.8f,
            genres = listOf("Action", "Crime", "Drama"),
            image = "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg",
        )
    }
}