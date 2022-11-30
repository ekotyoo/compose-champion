package com.ekotyoo.composechampion.ui.mapper

import com.ekotyoo.composechampion.domain.model.MovieDetail
import com.ekotyoo.composechampion.ui.screens.detail.model.MovieDetailUiModel

fun MovieDetail.toUiModel(): MovieDetailUiModel {
    return MovieDetailUiModel(
        id = this.id,
        title = this.title,
        year = this.year,
        genre = this.genre,
        cast = this.cast.map { it.toUiModel() },
        isFavorite = this.isFavorite,
        image = this.image,
        overview = this.overview,
        rating = this.rating
    )
}