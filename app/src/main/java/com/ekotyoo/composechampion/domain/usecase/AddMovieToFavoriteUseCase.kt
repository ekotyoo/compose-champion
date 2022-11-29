package com.ekotyoo.composechampion.domain.usecase

import com.ekotyoo.composechampion.domain.repository.MovieRepository

class AddMovieToFavoriteUseCase(private val movieRepository: MovieRepository) {
    fun invoke(movieId: String): Result<Boolean> = runCatching {
        movieRepository.favoriteMovie(movieId)
    }
}