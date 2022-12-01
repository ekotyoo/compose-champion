package com.ekotyoo.composechampion.ui.screens.detail

import com.ekotyoo.composechampion.ui.screens.detail.model.MovieDetailUiModel

data class MovieDetailUiState(
    val message: String? = null,
    val isLoading: Boolean = false,
    val data: MovieDetailUiModel? = null,
)