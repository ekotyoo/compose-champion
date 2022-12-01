package com.ekotyoo.composechampion.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ekotyoo.composechampion.domain.usecase.AddMovieToFavoriteUseCase
import com.ekotyoo.composechampion.domain.usecase.GetMovieDetailUseCase
import com.ekotyoo.composechampion.ui.mapper.toUiModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    movieId: String,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val addMovieToFavoriteUseCase: AddMovieToFavoriteUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<MovieDetailUiState> =
        MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState>
        get() = _uiState

    init {
        getMovieDetail(movieId)
    }

    private fun getMovieDetail(movieId: String) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getMovieDetailUseCase
                .invoke(movieId)
                .catch { e ->
                    _uiState.update {
                        it.copy(message = e.message)
                    }
                }
                .onCompletion {
                    _uiState.update { it.copy(isLoading = false) }
                }
                .collect { data ->
                    _uiState.update {
                        return@update if (data != null) {
                            it.copy(data = data.toUiModel())
                        } else {
                            it.copy(message = "Failed to load movie")
                        }
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

    fun messageShown() {
        _uiState.update {
            it.copy(message = null)
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