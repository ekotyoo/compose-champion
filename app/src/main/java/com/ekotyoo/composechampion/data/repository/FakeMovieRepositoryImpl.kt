package com.ekotyoo.composechampion.data.repository

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import com.ekotyoo.composechampion.data.fake.FakeMovieDataSource
import com.ekotyoo.composechampion.domain.model.MovieDetail
import com.ekotyoo.composechampion.domain.model.MovieListItem
import com.ekotyoo.composechampion.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FakeMovieRepositoryImpl(dataSource: FakeMovieDataSource) : MovieRepository {

    private val _movies = mutableListOf<MovieListItem>()
    private val _movieDetail = mutableListOf<MovieDetail>()

    init {
        _movies.addAll(dataSource.getMovieData())
        _movieDetail.addAll(dataSource.getMovieDetailData())
    }

    override fun getMovies(query: String): Flow<List<MovieListItem>> {
        return flowOf(_movies.filter { it.title.contains(query.toLowerCase(Locale.current)) })
    }

    override fun getMovieDetail(movieId: String): Flow<MovieDetail> {
        return flowOf(_movieDetail.first { it.id == movieId })
            .map { it.copy(isFavorite = _movies.first { m -> m.id == movieId }.isFavorite) }
    }

    override fun favoriteMovie(movieId: String, isFavorite: Boolean): Flow<Boolean> {
        val index = _movies.indexOfFirst { it.id == movieId }
        if (index >= 0) {
            _movies[index] = _movies[index].copy(isFavorite = isFavorite)
            return flowOf(true)
        }
        return flowOf(false)
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