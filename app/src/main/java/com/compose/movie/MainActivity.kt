package com.compose.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose.movie.navigation.BottomNavigationBar
import com.compose.movie.navigation.NavigationGraph
import com.compose.movie.ui.theme.MovieAppUsingJetpackComposeMVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppUsingJetpackComposeMVVMTheme {
                MainView()
            }
        }
    }

    @Composable
    private fun MainView() {
        val navController: NavHostController = rememberNavController()
        var buttonsVisible by remember { mutableStateOf(true) }

        Scaffold(
            bottomBar = {
                if (buttonsVisible) {
                    BottomNavigationBar(
                        navController = navController,
                        modifier = Modifier
                    )
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                NavigationGraph(navHostController = navController) { isVisible ->
                    buttonsVisible = isVisible
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieAppUsingJetpackComposeMVVMTheme {
        Greeting("Android")
    }
}