package com.compose.movie.core.presentation

//import SecurePreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.compose.movie.ui.theme.MovieAppUsingJetpackComposeMVVMTheme
import com.compose.movie.util.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val securePreferences = SecurePreferences(this)
        enableEdgeToEdge()
        setContent {
            MovieAppUsingJetpackComposeMVVMTheme {
//                MainView(securePreferences)
                SetBarColor(color = MaterialTheme.colorScheme.inverseOnSurface)
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.rout
                    ) {
                        composable(Screen.Home.rout) {
                            HomeScreen(navController = navController)
                        }

                        composable(
                            Screen.Details.rout + "/{movieId}",
                            arguments = listOf(
                                navArgument("movieId") {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStackEntry ->
//                            DetailsScreen(backStackEntry)

                        }
                    }
                }
            }
        }
    }

//    @Composable
//    fun MainView(securePreferences: SecurePreferences) {
//        val navController: NavHostController = rememberNavController()
//        var buttonsVisible by remember { mutableStateOf(true) }
//
//        Scaffold(
//            bottomBar = {
//                if (buttonsVisible) {
//                    BottomNavigationBar(
//                        navController = navController,
//                        modifier = Modifier
//                    )
//                }
//            }
//        ) { paddingValues ->
//            Box(
//                modifier = Modifier.padding(paddingValues)
//            ) {
//                NavigationGraph(
//                    navHostController = navController,
//                    securePreferences = securePreferences
//                ) { isVisible ->
//                    buttonsVisible = isVisible
//                }
//            }
//        }
//    }

    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        LaunchedEffect(key1 = color) {
            systemUiController.setSystemBarsColor(color)
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