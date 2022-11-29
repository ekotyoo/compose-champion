package com.ekotyoo.composechampion.domain.repository

import com.ekotyoo.composechampion.domain.model.MovieDetail
import com.ekotyoo.composechampion.domain.model.MovieListItem
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(query: String): Flow<List<MovieListItem>>
    fun getMovieDetail(movieId: String): Flow<MovieDetail>
    fun favoriteMovie(movieId: String): Boolean
}