package com.ekotyoo.composechampion.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ekotyoo.composechampion.common.UiState
import com.ekotyoo.composechampion.ui.mapper.toUiModel
import com.ekotyoo.composechampion.domain.usecase.AddMovieToFavoriteUseCase
import com.ekotyoo.composechampion.domain.usecase.GetMoviesUseCase
import com.ekotyoo.composechampion.ui.screens.home.model.MovieListItemUiModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val addMovieToFavoriteUseCase: AddMovieToFavoriteUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<MovieListItemUiModel>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<MovieListItemUiModel>>>
        get() = _uiState

    private val _searchQuery: MutableStateFlow<String> = MutableStateFlow("")
    val searchQuery: StateFlow<String>
        get() = _searchQuery

    init {
        searchMovies("")
    }

    fun onQueryChanged(query: String)  {
        _searchQuery.update { query }
        searchMovies(query)
    }

    private fun searchMovies(query: String) {
        viewModelScope.launch {
            getMoviesUseCase
                .invoke(query)
                .catch {
                    _uiState.update {
                        UiState.Error("Failed to load data")
                    }
                }
                .collect { data ->
                    _uiState.update {
                        UiState.Success(data.map { it.toUiModel() })
                    }
                }
        }
    }

    fun addMovieToFavorite(movieId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            addMovieToFavoriteUseCase.invoke(movieId, !isFavorite).collect { success ->
                if (success) searchMovies(_searchQuery.value)
            }
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