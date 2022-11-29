package com.ekotyoo.composechampion.domain.model

data class MovieDetail(
    val id: String,
    val title: String,
    val year: Int,
    val genre: List<String>,
    val cast: List<Cast>,
    val isFavorite: Boolean,
    val image: String,
    val overview: String,
)