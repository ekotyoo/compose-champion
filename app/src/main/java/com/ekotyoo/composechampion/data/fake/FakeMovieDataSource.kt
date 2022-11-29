package com.ekotyoo.composechampion.data.fake

import com.ekotyoo.composechampion.domain.model.Cast
import com.ekotyoo.composechampion.domain.model.MovieDetail
import com.ekotyoo.composechampion.domain.model.MovieListItem
import kotlin.random.Random

object FakeMovieDataSource {
    fun getMovieDetailData(): List<MovieDetail> = List(10) {
        MovieDetail(
            id = it.toString(),
            title = "Title - $it",
            year = 2000 + it,
            genre = List(it + 1) { genre -> "Genre - $genre" },
            cast = List(it + 1) { cast ->
                Cast(name = "Cast - $cast",
                    image = "https://via.placeholder.com/350x150")
            },
            isFavorite = false,
            image = "https://via.placeholder.com/350x150",
            overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
            rating = Random.nextFloat()
        )
    }

    fun getMovieData(): List<MovieListItem> = getMovieDetailData().map {
        MovieListItem(
            id = it.id,
            title = it.title,
            genre = it.genre,
            year = it.year,
            isFavorite = it.isFavorite,
            rating = it.rating,
            image = it.image,
        )
    }
}