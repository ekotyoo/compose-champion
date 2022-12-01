package com.ekotyoo.composechampion.ui.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ekotyoo.composechampion.di.Injection
import com.ekotyoo.composechampion.ui.screens.about.AboutScreen
import com.ekotyoo.composechampion.ui.screens.detail.MovieDetailScreen
import com.ekotyoo.composechampion.ui.screens.detail.MovieDetailViewModel
import com.ekotyoo.composechampion.ui.screens.home.HomeScreen
import com.ekotyoo.composechampion.ui.screens.home.HomeViewModel

fun NavGraphBuilder.createNavGraph(navController: NavHostController) {
    composable(Screen.Home.route) {
        HomeScreen(
            viewModel = viewModel(factory = HomeViewModel.Factory(
                getMoviesUseCase = Injection.provideGetMoviesUseCase(),
                addMovieToFavoriteUseCase = Injection.provideAddMoveToFavoriteUseCase(),
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
        AboutScreen(onNavigateBack = { navController.navigateUp() })
    }
    composable(Screen.MovieDetail.route) {
        val movieId = it.arguments?.getString("movieId")
        if (movieId != null) {
            MovieDetailScreen(
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