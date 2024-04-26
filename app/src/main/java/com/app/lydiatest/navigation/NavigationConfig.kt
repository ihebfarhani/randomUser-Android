package com.app.lydiatest.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.lydiatest.presenter.screens.details.UserDetailsScreen
import com.app.lydiatest.presenter.screens.main.HomeScreen
import com.app.lydiatest.presenter.screens.main.MainViewModel
import com.app.lydiatest.presenter.screens.splash.SplashScreen

@Composable
fun NavigationConfig() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<MainViewModel>()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH_SCREEN.name
    ) {

        composable(
            route = Routes.SPLASH_SCREEN.name
        ) {
            SplashScreen(navController = navController)
        }

        composable(
            route = Routes.MAIN_SCREEN.name
        ) {
            HomeScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = Routes.DETAILS_SCREEN.name
        ) {
            UserDetailsScreen(navController = navController, viewModel = viewModel)
        }

    }
}

enum class Routes {
    SPLASH_SCREEN,
    MAIN_SCREEN,
    DETAILS_SCREEN,
}