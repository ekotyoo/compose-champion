package com.ekotyoo.composechampion.ui.screens.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ekotyoo.composechampion.domain.usecase.AddMovieToFavoriteUseCase
import com.ekotyoo.composechampion.domain.usecase.GetFavoriteMoviesUseCase
import com.ekotyoo.composechampion.ui.mapper.toUiModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AboutViewModel(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val addMovieToFavoriteUseCase: AddMovieToFavoriteUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<AboutUiState> = MutableStateFlow(AboutUiState())
    val uiState: StateFlow<AboutUiState> = _uiState.asStateFlow()

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            getFavoriteMoviesUseCase
                .invoke()
                .catch { e ->
                    _uiState.update { it.copy(message = e.message) }
                }
                .onCompletion {
                    _uiState.update { it.copy(isLoading = false) }
                }
                .collect { data ->
                    _uiState.update { state ->
                        state.copy(
                            data = data.map { d -> d.toUiModel() }
                        )
                    }
                }
        }
    }

    fun addMovieToFavorite(movieId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            addMovieToFavoriteUseCase.invoke(movieId, !isFavorite).collect { success ->
                if (success) getFavoriteMovies()
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
        private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
        private val addMovieToFavoriteUseCase: AddMovieToFavoriteUseCase,
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AboutViewModel::class.java)) {
                return AboutViewModel(
                    getFavoriteMoviesUseCase = getFavoriteMoviesUseCase,
                    addMovieToFavoriteUseCase = addMovieToFavoriteUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }
    }
}