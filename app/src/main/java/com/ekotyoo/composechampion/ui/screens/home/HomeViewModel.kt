package com.ekotyoo.composechampion.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ekotyoo.composechampion.domain.usecase.AddMovieToFavoriteUseCase
import com.ekotyoo.composechampion.domain.usecase.GetMoviesUseCase
import com.ekotyoo.composechampion.ui.mapper.toUiModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val addMovieToFavoriteUseCase: AddMovieToFavoriteUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        searchMovies(_uiState.value.searchQuery)
    }

    fun onQueryChanged(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
        searchMovies(query)
    }

    private fun searchMovies(query: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            getMoviesUseCase
                .invoke(query)
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
                if (success) searchMovies(_uiState.value.searchQuery)
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
        private val getMoviesUseCase: GetMoviesUseCase,
        private val addMovieToFavoriteUseCase: AddMovieToFavoriteUseCase,
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(
                    getMoviesUseCase = getMoviesUseCase,
                    addMovieToFavoriteUseCase = addMovieToFavoriteUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }
    }
}