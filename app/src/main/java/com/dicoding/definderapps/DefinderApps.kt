package com.dicoding.definderapps

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicoding.definderapps.ui.home.HomeScreen
import com.dicoding.definderapps.ui.login.LoginScreen
import com.dicoding.definderapps.ui.navigation.Screen
import com.dicoding.definderapps.ui.register.RegisterScreen

@Composable
fun DefinderApp(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = Screen.Register.route
    ){
        composable(Screen.Login.route){
            LoginScreen(
                navigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                navigateToHome = {
                    navController.navigate(Screen.Home.route)
                }
            )
        }
        composable(Screen.Register.route){
            RegisterScreen(
                navigateToLogin = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }
        composable(Screen.Home.route){
            HomeScreen()
        }
    }
}