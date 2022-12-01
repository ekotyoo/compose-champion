package com.ekotyoo.composechampion.ui.screens.detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekotyoo.composechampion.R
import com.ekotyoo.composechampion.ui.theme.ComposeChampionTheme
import com.ekotyoo.composechampion.ui.theme.Red40

@Composable
fun FavoriteButton(
    onClick: () -> Unit,
    isFavorite: Boolean = false,
) {
    Button(
        onClick = onClick,
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Outlined.Favorite,
            contentDescription = stringResource(id = R.string.desc_favorite_button),
            tint = if (isFavorite) Red40 else MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = stringResource(id = if (isFavorite) R.string.label_remove_from_favorite else R.string.label_add_to_favorite))
    }
}

@Preview
@Composable
fun FavoriteButtonPreview() {
    ComposeChampionTheme {
        FavoriteButton(onClick = {})
    }
}