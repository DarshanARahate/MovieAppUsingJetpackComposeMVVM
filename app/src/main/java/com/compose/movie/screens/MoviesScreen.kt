package com.compose.movie.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.compose.movie.navigation.BottomNavItem

@Composable
fun MoviesScreen(navHostController: NavHostController) {
    Column {
        Button(onClick = { navHostController.navigate(BottomNavItem.MovieDetails.route) }) {
            Text(text = "Movies Screen")
        }

    }
}