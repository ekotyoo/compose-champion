package com.ekotyoo.composechampion.ui.mapper

import com.ekotyoo.composechampion.domain.model.MovieListItem
import com.ekotyoo.composechampion.ui.screens.home.model.MovieListItemUiModel

fun MovieListItem.toUiModel(): MovieListItemUiModel {
    return MovieListItemUiModel(
        id = this.id,
        title = this.title,
        rating = this.rating,
        genre = this.genre,
        year = this.year,
        image = this.image,
        isFavorite = this.isFavorite
    )
}