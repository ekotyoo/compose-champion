package com.ekotyoo.composechampion.domain.usecase

import com.ekotyoo.composechampion.domain.model.MovieDetail
import com.ekotyoo.composechampion.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetail(private val movieRepository: MovieRepository) {
    fun invoke(movieId: String): Flow<MovieDetail> = movieRepository.getMovieDetail(movieId)
}