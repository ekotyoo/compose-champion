package com.ekotyoo.composechampion.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ekotyoo.composechampion.R

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
) {
    IconButton(
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
        onClick = onNavigateBack,
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = stringResource(id = R.string.desc_back_button),
        )
    }
}