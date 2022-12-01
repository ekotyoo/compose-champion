package com.ekotyoo.composechampion.ui.screens.home

import com.ekotyoo.composechampion.common.Status
import com.ekotyoo.composechampion.ui.screens.home.model.MovieListItemUiModel

data class HomeUiState(
    val status: Status = Status.Loading,
    val searchQuery: String = "",
    val data: List<MovieListItemUiModel> = emptyList()
)
