package com.ekotyoo.composechampion.ui.screens.about

import com.ekotyoo.composechampion.ui.screens.home.model.MovieListItemUiModel

data class AboutUiState(
    val message: String? = null,
    val isLoading: Boolean = false,
    val data: List<MovieListItemUiModel> = emptyList()
)