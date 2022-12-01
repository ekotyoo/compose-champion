package com.ekotyoo.composechampion.data.repository

import com.ekotyoo.composechampion.data.fake.FakeMovieDataSource
import com.ekotyoo.composechampion.domain.model.MovieDetail
import com.ekotyoo.composechampion.domain.model.MovieListItem
import com.ekotyoo.composechampion.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class FakeMovieRepositoryImpl(dataSource: FakeMovieDataSource) : MovieRepository {

    private val _movies = mutableListOf<MovieListItem>()
    private val _movieDetail = mutableListOf<MovieDetail>()

    init {
        _movies.addAll(dataSource.getMovieData())
        _movieDetail.addAll(dataSource.getMovieDetailData())
    }

    override fun getMovies(query: String): Flow<List<MovieListItem>> = flow {
        emit(_movies.filter { it.title.contains(query, ignoreCase = true) })
    }

    override fun getMovieDetail(movieId: String): Flow<MovieDetail> = flow {
        emit(_movieDetail.first { it.id == movieId })
    }.map { it.copy(isFavorite = _movies.first { m -> m.id == movieId }.isFavorite) }

    override fun favoriteMovie(movieId: String, isFavorite: Boolean): Flow<Boolean> = flow {
        val index = _movies.indexOfFirst { it.id == movieId }
        if (index >= 0) {
            _movies[index] = _movies[index].copy(isFavorite = isFavorite)
            emit(true)
            return@flow
        }
        emit(false)
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