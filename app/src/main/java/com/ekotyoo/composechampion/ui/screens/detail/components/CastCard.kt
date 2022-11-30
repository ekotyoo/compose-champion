package com.ekotyoo.composechampion.ui.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ekotyoo.composechampion.ui.theme.ComposeChampionTheme

@Composable
fun CastCard(
    name: String,
    playedAs: String,
    image: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Red)
                .size(50.dp)
        )
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        Text(
            text = playedAs,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CastCardPreview() {
    ComposeChampionTheme {
        CastCard(
            name = "Deane Keaton",
            playedAs = "Sri Asih",
            image = "https://m.media-amazon.com/images/M/MV5BMTY5NDI5OTEyOF5BMl5BanBnXkFtZTgwMzU4NDI1NzM@._V1_UY1200_CR108,0,630,1200_AL_.jpg"
        )
    }
}