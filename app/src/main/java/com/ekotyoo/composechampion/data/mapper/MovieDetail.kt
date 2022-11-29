package com.ekotyoo.composechampion.data.mapper

import com.ekotyoo.composechampion.domain.model.MovieDetail
import com.ekotyoo.composechampion.ui.screens.detail.model.MovieDetailUiModel

fun MovieDetailUiModel.fromDomain(movie: MovieDetail): MovieDetailUiModel {
    return MovieDetailUiModel(
        id = movie.id,
        title = movie.title,
        year = movie.year,
        genre = movie.genre,
        cast = movie.cast,
        isFavorite = movie.isFavorite,
        image = movie.image,
        overview = movie.overview,
    )
}