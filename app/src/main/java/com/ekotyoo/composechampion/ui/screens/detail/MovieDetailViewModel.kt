package com.ekotyoo.composechampion.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ekotyoo.composechampion.common.UiState
import com.ekotyoo.composechampion.domain.usecase.AddMovieToFavoriteUseCase
import com.ekotyoo.composechampion.domain.usecase.GetMovieDetailUseCase
import com.ekotyoo.composechampion.ui.mapper.toUiModel
import com.ekotyoo.composechampion.ui.screens.detail.model.MovieDetailUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    movieId: String,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val addMovieToFavoriteUseCase: AddMovieToFavoriteUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<MovieDetailUiModel>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<MovieDetailUiModel>>
        get() = _uiState

    init {
        getMovieDetail(movieId)
    }

    private fun getMovieDetail(movieId: String) {
        viewModelScope.launch {
            getMovieDetailUseCase
                .invoke(movieId)
                .catch {
                    _uiState.update {
                        UiState.Error("Failed to load data")
                    }
                }
                .collect { data ->
                    _uiState.update {
                        UiState.Success(data.toUiModel())
                    }
                }
        }
    }

    fun addMovieToFavorite(movieId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            addMovieToFavoriteUseCase.invoke(movieId, !isFavorite).collect { success ->
                if (success) getMovieDetail(movieId)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val movieId: String,
        private val getMovieDetailUseCase: GetMovieDetailUseCase,
        private val addMovieToFavoriteUseCase: AddMovieToFavoriteUseCase,
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
                return MovieDetailViewModel(
                    movieId = movieId,
                    getMovieDetailUseCase = getMovieDetailUseCase,
                    addMovieToFavoriteUseCase = addMovieToFavoriteUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }
    }
}