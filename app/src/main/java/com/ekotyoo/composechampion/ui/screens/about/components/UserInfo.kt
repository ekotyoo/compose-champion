package com.ekotyoo.composechampion.ui.screens.about.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UserInfo(
    modifier: Modifier = Modifier,
    email: String,
    name: String,
) {
    Column(modifier = modifier) {
        Text(text = email, style = MaterialTheme.typography.titleLarge)
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}