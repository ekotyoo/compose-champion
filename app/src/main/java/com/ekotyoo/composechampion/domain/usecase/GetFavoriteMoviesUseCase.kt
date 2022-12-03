package com.ekotyoo.composechampion.domain.usecase

import com.ekotyoo.composechampion.domain.model.MovieListItem
import com.ekotyoo.composechampion.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMoviesUseCase(private val movieRepository: MovieRepository) {
    fun invoke(): Flow<List<MovieListItem>> = movieRepository.getFavoriteMovies()
}