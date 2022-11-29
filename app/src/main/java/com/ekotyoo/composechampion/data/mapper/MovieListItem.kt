package com.ekotyoo.composechampion.data.mapper

import com.ekotyoo.composechampion.ui.screens.home.model.MovieListItemUiModel

fun MovieListItemUiModel.toUiModel(): MovieListItemUiModel {
    return MovieListItemUiModel(
        title = this.title,
        rating = this.rating,
        genre = this.genre,
        year = this.year,
        image = this.image,
        isFavorite = this.isFavorite
    )
}