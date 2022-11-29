package com.ekotyoo.composechampion.ui.screens.detail.model

import com.ekotyoo.composechampion.domain.model.Cast

data class MovieDetailUiModel(
    val id: String,
    val title: String,
    val year: Int,
    val genre: List<String>,
    val cast: List<Cast>,
    val isFavorite: Boolean,
    val image: String,
    val overview: String,
)