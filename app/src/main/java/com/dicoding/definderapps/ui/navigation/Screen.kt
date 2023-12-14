package com.dicoding.definderapps.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Detail : Screen("home/{id}") {
        fun createRoute(id: Int) = "home/$id"
    }

    object Search : Screen("search")
    object Mbti : Screen("mbti")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DarkMode:Screen("dark_mode")
}