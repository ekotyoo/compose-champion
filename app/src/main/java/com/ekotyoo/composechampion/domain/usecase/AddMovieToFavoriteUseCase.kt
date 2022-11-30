package com.ekotyoo.composechampion.domain.usecase

import com.ekotyoo.composechampion.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class AddMovieToFavoriteUseCase(private val movieRepository: MovieRepository) {
    fun invoke(movieId: String, isFavorite: Boolean): Flow<Boolean> =
        movieRepository.favoriteMovie(movieId, isFavorite)
}