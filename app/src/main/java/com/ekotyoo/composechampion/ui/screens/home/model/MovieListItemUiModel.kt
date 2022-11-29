package com.ekotyoo.composechampion.ui.screens.home.model

data class MovieListItemUiModel(
    val title: String,
    val rating: Float,
    val genre: List<String>,
    val year: Int,
    val image: String,
    val isFavorite: Boolean= false
)
