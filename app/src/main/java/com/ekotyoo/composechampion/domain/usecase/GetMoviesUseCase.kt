package com.ekotyoo.composechampion.domain.usecase

import com.ekotyoo.composechampion.domain.model.MovieListItem
import com.ekotyoo.composechampion.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    fun invoke(query: String): Flow<List<MovieListItem>> = movieRepository.getMovies(query)
}