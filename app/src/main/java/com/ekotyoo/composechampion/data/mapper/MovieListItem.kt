package com.ekotyoo.composechampion.data.mapper

import com.ekotyoo.composechampion.domain.model.MovieListItem
import com.ekotyoo.composechampion.ui.screens.home.model.MovieListItemUiModel

fun MovieListItemUiModel.fromDomain(movie: MovieListItem): MovieListItemUiModel {
    return MovieListItemUiModel(
        title = movie.title,
        rating = movie.rating,
        genre  = movie.genre,
        year = movie.year,
        image = movie.image,
        isFavorite = movie.isFavorite
    )
}