package com.dicoding.definderapps

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.definderapps.ui.profile.ProfileViewModel
import com.dicoding.definderapps.ui.theme.DefinderAppsTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: ProfileViewModel = viewModel(factory= ViewModelFactory.getInstance(LocalContext.current))
            val getTheme by viewModel.getTheme().collectAsState(initial = isSystemInDarkTheme())
            Log.d("theme phone", "${isSystemInDarkTheme()}")
            DefinderAppsTheme(darkTheme = getTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    DefinderApp(
                        darkTheme = getTheme,
                        onThemeUpdated = {
                            viewModel.saveTheme(it)
                        } ,
                        navController = navController

                    )
                }
            }
        }
    }
}