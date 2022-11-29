package com.ekotyoo.composechampion.ui.screens.detail.model

data class MovieDetailUiModel(
    val id: String,
    val title: String,
    val year: Int,
    val genre: List<String>,
    val cast: List<CastUiModel>,
    val isFavorite: Boolean,
    val image: String,
    val overview: String,
    val rating: Float,
)