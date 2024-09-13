package com.compose.movie.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val lable: String) {
    object Movies : BottomNavItem("movies", Icons.Default.Menu, "Movies")
    object UserProfile : BottomNavItem("userprofile", Icons.Default.AccountCircle, "User Profile")
    object Settings : BottomNavItem("settings", Icons.Default.Settings, "Settings")
//    object About : BottomNavItem("about", Icons.Default.Info, "About")
    object MovieDetails : BottomNavItem("moviedetails", Icons.Default.PlayArrow, "MovieDetails")
}