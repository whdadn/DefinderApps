package com.dicoding.definderapps

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.definderapps.ui.detail.DetailScreen
import com.dicoding.definderapps.ui.favorite.FavoriteScreen
import com.dicoding.definderapps.ui.home.HomeScreen
import com.dicoding.definderapps.ui.login.LoginScreen
import com.dicoding.definderapps.ui.login.LoginViewModel
import com.dicoding.definderapps.ui.mbti.MbtiScreen
import com.dicoding.definderapps.ui.navigation.NavigationItem
import com.dicoding.definderapps.ui.navigation.Screen
import com.dicoding.definderapps.ui.profile.ProfileScreen
import com.dicoding.definderapps.ui.register.RegisterScreen
import com.dicoding.definderapps.ui.search.SearchScreen

@Composable
fun DefinderApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel:LoginViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current)),
    darkTheme: Boolean, onThemeUpdated: (Boolean) -> Unit
) {

    val session by viewModel.getSession().observeAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val startDestination = when (session?.isLogin) {
        false -> {
            Screen.Login.route
        }
        else -> {
            Screen.Home.route
        }
    }
    Scaffold(
        bottomBar = {
            if (currentRoute !in listOf(Screen.Detail.route, Screen.Login.route, Screen.Register.route)) {
                BottomBar(navController = navController, modifier = Modifier.heightIn(max = 60.dp))
            }
        },
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier.padding(paddingValues),

        ) {
            composable(Screen.Login.route) {
                LoginScreen(
                    navigateToRegister = {
                        navController.navigate(Screen.Register.route)
                    },
                    navigateToHome = {
                        navController.navigate(Screen.Home.route)
                    }
                )
            }
            composable(Screen.Register.route) {
                RegisterScreen(
                    navigateToLogin = {
                        navController.navigate(Screen.Login.route)
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    }
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("id") ?: -1
                DetailScreen(id = id)
            }
            composable(Screen.Mbti.route) {
                MbtiScreen()
            }
            composable(Screen.Search.route) {
                SearchScreen()
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    navigateToLogin = {
                        navController.navigate(Screen.Login.route)
                    },
                    darkTheme = darkTheme,
                    onThemeUpdated = onThemeUpdated
                )
            }
        }
    }

}


@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color(0xF0000080),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItem = listOf(
            NavigationItem(
                icon = painterResource(id = R.drawable.ic_home),
                screen = Screen.Home
            ),
            NavigationItem(
                icon = painterResource(id = R.drawable.ic_search),
                screen = Screen.Search
            ),
            NavigationItem(
                icon = painterResource(id = R.drawable.ic_mbti),
                screen = Screen.Mbti
            ),
            NavigationItem(
                icon = painterResource(id = R.drawable.ic_favorite),
                screen = Screen.Favorite
            ),
            NavigationItem(
                icon = painterResource(id = R.drawable.ic_profile),
                screen = Screen.Profile
            )
        )
        navigationItem.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = null,
                        tint = if (currentRoute == item.screen.route) {
                            Color.Black
                        } else {
                            Color.White
                        }
                    )
                },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }

}
