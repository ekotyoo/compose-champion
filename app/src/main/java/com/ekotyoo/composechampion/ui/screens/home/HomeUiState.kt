package com.ekotyoo.composechampion.ui.screens.home

import com.ekotyoo.composechampion.common.Status
import com.ekotyoo.composechampion.ui.screens.home.model.MovieListItemUiModel

data class HomeUiState(
    val message: String? = null,
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val data: List<MovieListItemUiModel> = emptyList()
)
