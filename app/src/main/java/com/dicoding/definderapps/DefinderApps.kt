package com.dicoding.definderapps

import android.widget.Toast
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.navigation.navArgument
import com.dicoding.definderapps.ui.detail.DetailScreen
import com.dicoding.definderapps.ui.detail.transportation.DetailTransportationScreen
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
import com.yogi.foodlist.ui.common.UiState

@Composable
fun DefinderApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel:LoginViewModel = viewModel(factory = ViewModelFactory.getInstance(LocalContext.current)),
    darkTheme: Boolean,
    onThemeUpdated: (Boolean) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {uiState->
        when(uiState){
            is UiState.Loading->{
                viewModel.getSession()
            }
            is UiState.Success->{
                val startDestination = when(uiState.data.isLogin){
                    false->{
                        Screen.Login.route
                    }
                    else->{
                        Screen.Home.route
                    }

                }
                DefinderAppContent(
                    modifier = modifier,
                    navController = navController,
                    darkTheme = darkTheme,
                    onThemeUpdated = onThemeUpdated,
                    startDestination = startDestination
                )

            }
            is UiState.Error->{
                Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_SHORT ).show()
            }
        }
    }
}

@Composable
fun DefinderAppContent(
    modifier:Modifier = Modifier,
    navController: NavHostController,
    darkTheme: Boolean,
    onThemeUpdated: (Boolean) -> Unit,
    startDestination :String,
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            val bottomNav = listOf(
                Screen.Home.route,Screen.Search.route,Screen.Profile.route, Screen.Mbti.route, Screen.Favorite.route
            )
            if (currentRoute in bottomNav) {
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
            composable(
                route = Screen.Login.route,
            ) {
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
                        navController.popBackStack()
                        navController.navigate(Screen.Login.route)
                    }
                )
            }
            composable(
                route= Screen.Home.route,
            ) {
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
                DetailScreen(
                    id = id,
                    navigateToDetailTransport = { idDestination,transportType->
                        navController.navigate(Screen.DetailTransport.createRoute(idDestination,transportType))
                    }
                )
            }
            composable(Screen.Mbti.route) {
                MbtiScreen()
            }
            composable(Screen.Search.route) {
                SearchScreen()
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen { id ->
                    navController.navigate(Screen.Detail.createRoute(id))
                }
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    navigateToLogin = {
                        navController.popBackStack()
                        navController.navigate(Screen.Login.route)
                    },
                    darkTheme = darkTheme,
                    onThemeUpdated = onThemeUpdated
                )
            }
            composable(
                route = Screen.DetailTransport.route,
                arguments = listOf(
                    navArgument("idDestination") { type = NavType.IntType },
                    navArgument("transportType") {type = NavType.StringType}
                    ),
                ){
                val idDestination= it.arguments?.getInt("idDestination") ?: -1
                val transportType = it.arguments?.getString("transportType") ?: ""
                DetailTransportationScreen(idDestination = idDestination, transportType = transportType, navigateBack = {navController.navigateUp()})
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
                icon = painterResource(id = R.drawable.home_vector),
                screen = Screen.Home
            ),
            NavigationItem(
                icon = painterResource(id = R.drawable.search_vector),
                screen = Screen.Search
            ),
            NavigationItem(
                icon = painterResource(id = R.drawable.favorite_vector),
                screen = Screen.Favorite
            ),
            NavigationItem(
                icon = painterResource(id = R.drawable.profile_vector),
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
