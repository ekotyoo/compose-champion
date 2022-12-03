package com.ekotyoo.composechampion.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ekotyoo.composechampion.di.Injection
import com.ekotyoo.composechampion.ui.screens.about.AboutScreen
import com.ekotyoo.composechampion.ui.screens.about.AboutViewModel
import com.ekotyoo.composechampion.ui.screens.detail.MovieDetailScreen
import com.ekotyoo.composechampion.ui.screens.detail.MovieDetailViewModel
import com.ekotyoo.composechampion.ui.screens.home.HomeScreen
import com.ekotyoo.composechampion.ui.screens.home.HomeViewModel

fun NavGraphBuilder.createNavGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
) {
    composable(Screen.Home.route) {
        HomeScreen(
            snackbarHostState = snackbarHostState,
            viewModel = viewModel(factory = HomeViewModel.Factory(
                Injection.provideGetMoviesUseCase(),
                Injection.provideAddMoveToFavoriteUseCase(),
            )),
            onNavigateToAboutScreen = {
                navController.navigate(Screen.About.route) {
                    launchSingleTop = true
                }
            },
            onNavigateToDetailScreen = {
                navController.navigate(Screen.MovieDetail.routeFromId(it)) {
                    launchSingleTop = true
                }
            }
        )
    }
    composable(Screen.About.route) {
        AboutScreen(
            onNavigateBack = { navController.navigateUp() },
            onNavigateToDetail = {
                navController.navigate(Screen.MovieDetail.routeFromId(it)) {
                    launchSingleTop = true
                }
            },
            snackbarHostState = snackbarHostState,
            viewModel = viewModel(
                factory = AboutViewModel.Factory(
                    Injection.provideGetFavoriteMoviesUseCase(),
                    Injection.provideAddMoveToFavoriteUseCase(),
                ),
            ),
        )
    }
    composable(Screen.MovieDetail.route) {
        val movieId = it.arguments?.getString("movieId")
        if (movieId != null) {
            MovieDetailScreen(
                snackbarHostState = snackbarHostState,
                viewModel = viewModel(
                    factory = MovieDetailViewModel.Factory(
                        movieId,
                        Injection.provideGetMovieDetailUseCase(),
                        Injection.provideAddMoveToFavoriteUseCase(),
                    ),
                ),
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}