package com.ekotyoo.composechampion.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ekotyoo.composechampion.ui.navigation.Screen
import com.ekotyoo.composechampion.ui.screens.detail.AboutScreen
import com.ekotyoo.composechampion.ui.screens.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(modifier = modifier) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
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
    }
}