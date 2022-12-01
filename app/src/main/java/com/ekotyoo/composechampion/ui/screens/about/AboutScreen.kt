package com.ekotyoo.composechampion.ui.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ekotyoo.composechampion.R
import com.ekotyoo.composechampion.common.components.BackButton

@Composable
fun AboutScreen(modifier: Modifier = Modifier, onNavigateBack: () -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(200.dp))
            AsyncImage(
                model = stringResource(id = R.string.url_profile_picture),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .size(160.dp)

            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.label_profile_name), style = MaterialTheme.typography.titleMedium)
            Text(text = stringResource(id = R.string.label_profile_email), style = MaterialTheme.typography.bodyMedium)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(onNavigateBack = onNavigateBack)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = stringResource(id = R.string.label_about),
                style = MaterialTheme.typography.titleLarge)
        }
    }
}