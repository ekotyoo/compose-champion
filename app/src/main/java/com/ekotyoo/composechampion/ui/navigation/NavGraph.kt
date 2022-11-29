package com.ekotyoo.composechampion.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ekotyoo.composechampion.ui.screens.detail.AboutScreen
import com.ekotyoo.composechampion.ui.screens.home.HomeScreen

fun NavGraphBuilder.createNavGraph(navController: NavHostController) {
    composable(Screen.Home.route) {
        HomeScreen(
            onNavigateToAboutScreen = {
                navController.navigate(Screen.About.route) {
                    launchSingleTop = true
                }
            },
        )
    }
    composable(Screen.About.route) {
        AboutScreen()
    }
    composable(Screen.MovieDetail.route) {

    }
}