package com.ekotyoo.composechampion.ui.screens.detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun MovieHeader(
    title: String,
    rating: Float,
    year: Int,
    genre: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            maxLines = 3,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Rounded.Star,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "$rating |",
                style = MaterialTheme.typography.labelMedium,
                maxLines = 2, overflow = TextOverflow.Ellipsis,
            )
            Icon(
                imageVector = Icons.Rounded.CalendarMonth,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "$year |",
                style = MaterialTheme.typography.labelMedium,
                maxLines = 2, overflow = TextOverflow.Ellipsis,
            )
            Icon(
                imageVector = Icons.Rounded.Movie,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = genre,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 2, overflow = TextOverflow.Ellipsis,
            )
        }
    }
}