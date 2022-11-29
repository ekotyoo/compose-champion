package com.ekotyoo.composechampion.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object MovieDetail : Screen("detail/{movieId}") {
        fun routeFromId(movieId: String) = "detail/$movieId"
    }
}