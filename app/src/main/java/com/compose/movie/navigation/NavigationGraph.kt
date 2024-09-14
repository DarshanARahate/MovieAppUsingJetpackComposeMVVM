package com.compose.movie.navigation

import SecurePreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.movie.screens.AboutScreen
import com.compose.movie.screens.MovieDetailsScreen
import com.compose.movie.screens.MoviesScreen
import com.compose.movie.screens.PreviewSS
import com.compose.movie.screens.SettingsScreen
import com.compose.movie.screens.UserProfile


@Composable
fun NavigationGraph(navHostController: NavHostController,
                    securePreferences : SecurePreferences,
                    onBottomVisibilityChanged: (Boolean) -> Unit) {
    
    NavHost(navController = navHostController, startDestination = BottomNavItem.Movies.route) {
        composable(BottomNavItem.Movies.route) {
            onBottomVisibilityChanged(true)
            MoviesScreen(navHostController)
        }
        composable(BottomNavItem.UserProfile.route) {
            onBottomVisibilityChanged(true)
            UserProfile()
        }
        composable(BottomNavItem.Settings.route) {
            onBottomVisibilityChanged(true)
            PreviewSS()
            SettingsScreen(securePreferences)
        }
//        composable(BottomNavItem.About.route) {
//            onBottomVisibilityChanged(true)
//            AboutScreen()
//        }
        composable(BottomNavItem.MovieDetails.route) {
            onBottomVisibilityChanged(false)
            MovieDetailsScreen()
        }
    }
}

private fun onMovieDetailScreen() {
    println("onMovieDetailScreen Clicked")
}