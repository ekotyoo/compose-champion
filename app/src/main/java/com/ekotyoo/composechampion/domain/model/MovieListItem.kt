package com.ekotyoo.composechampion.domain.model

data class MovieListItem(
    val id: String,
    val title: String,
    val genre: List<String>,
    val year: Int,
    val isFavorite: Boolean,
    val rating: Float,
    val image: String,
)