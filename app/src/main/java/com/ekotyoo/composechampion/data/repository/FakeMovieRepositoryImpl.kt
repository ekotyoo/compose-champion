package com.ekotyoo.composechampion.data.repository

import com.ekotyoo.composechampion.data.fake.FakeMovieDataSource
import com.ekotyoo.composechampion.domain.model.MovieDetail
import com.ekotyoo.composechampion.domain.model.MovieListItem
import com.ekotyoo.composechampion.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull

class FakeMovieRepositoryImpl(private val _dataSource: FakeMovieDataSource) : MovieRepository {

    override fun getMovies(query: String): Flow<List<MovieListItem>> = flow {
        emit(_dataSource.getMovies(query))
    }

    override fun getMovieDetail(movieId: String): Flow<MovieDetail?> = flow {
        emit(_dataSource.getMovieDetail(movieId))
    }.mapNotNull {
        it?.copy(isFavorite = _dataSource.getMovies().first { m -> m.id == movieId }.isFavorite)
    }

    override fun favoriteMovie(movieId: String, isFavorite: Boolean): Flow<Boolean> = flow {
        emit(_dataSource.favoriteMovie(movieId, isFavorite))
    }

    companion object {
        @Volatile
        private var instance: FakeMovieRepositoryImpl? = null

        fun getInstance(dataSource: FakeMovieDataSource): FakeMovieRepositoryImpl {
            return instance ?: synchronized(this) {
                FakeMovieRepositoryImpl(dataSource).apply { instance = this }
            }
        }
    }
}