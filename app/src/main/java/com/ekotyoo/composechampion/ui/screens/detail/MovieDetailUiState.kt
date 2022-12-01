package com.ekotyoo.composechampion.ui.screens.detail

import com.ekotyoo.composechampion.common.Status
import com.ekotyoo.composechampion.ui.screens.detail.model.MovieDetailUiModel

data class MovieDetailUiState(
    val status: Status = Status.Loading,
    val data: MovieDetailUiModel? = null,
)