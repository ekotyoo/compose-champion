package com.ekotyoo.composechampion.di

import com.ekotyoo.composechampion.data.fake.FakeMovieDataSource
import com.ekotyoo.composechampion.data.repository.FakeMovieRepositoryImpl
import com.ekotyoo.composechampion.domain.repository.MovieRepository
import com.ekotyoo.composechampion.domain.usecase.AddMovieToFavoriteUseCase
import com.ekotyoo.composechampion.domain.usecase.GetMovieDetailUseCase
import com.ekotyoo.composechampion.domain.usecase.GetMoviesUseCase

object Injection {
    private fun provideDataSource(): FakeMovieDataSource = FakeMovieDataSource
    private fun provideMovieRepository(): MovieRepository =
        FakeMovieRepositoryImpl.getInstance(provideDataSource())

    fun provideAddMoveToFavoriteUseCase(): AddMovieToFavoriteUseCase =
        AddMovieToFavoriteUseCase(provideMovieRepository())

    fun provideGetMovieDetailUseCase(): GetMovieDetailUseCase =
        GetMovieDetailUseCase(provideMovieRepository())

    fun provideGetMoviesUseCase(): GetMoviesUseCase = GetMoviesUseCase(provideMovieRepository())
}