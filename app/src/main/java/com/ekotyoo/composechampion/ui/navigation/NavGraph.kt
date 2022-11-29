package com.ekotyoo.composechampion.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ekotyoo.composechampion.ui.screens.about.AboutScreen
import com.ekotyoo.composechampion.ui.screens.detail.MovieDetailScreen
import com.ekotyoo.composechampion.ui.screens.home.HomeScreen

fun NavGraphBuilder.createNavGraph(navController: NavHostController) {
    composable(Screen.Home.route) {
        HomeScreen(
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
        AboutScreen()
    }
    composable(Screen.MovieDetail.route) {
        val args = it.arguments?.getString("movieId")
        if (args != null) {
            MovieDetailScreen(movieId = args)
        }
    }
}