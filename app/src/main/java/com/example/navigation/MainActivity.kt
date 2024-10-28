package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigation.model.Routes
import com.example.navigation.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme {
                val navController = rememberNavController()
                MainApp(navController)
            }
        }
    }
}

@Composable
fun MainApp(navController: NavHostController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavigationHost(
            navigationController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun NavigationHost(navigationController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navigationController,
        startDestination = Routes.Pantalla1.route,
        modifier = modifier
    ) {
        composable(Routes.Pantalla1.route) { Screen1(navigationController) }
        composable(Routes.Pantalla2.route) { Screen2(navigationController) }
        composable(Routes.Pantalla3.route) { Screen3(navigationController) }
        /*composable(Routes.Pantalla4.route + "/{name}") { backStackEntry ->
            Screen4(
                navigationController,
                backStackEntry.arguments?.getString("name").orEmpty()
            )
        }*/
        composable(
            Routes.Pantalla4.route,
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            Screen4(
                navigationController,
                backStackEntry.arguments?.getString("name").orEmpty()
            )
        }

        composable(
            Routes.Pantalla5.route,
            arguments = listOf(navArgument("age", { defaultValue = 0 }))
        ) { backStackEntry ->
            Screen5(
                navigationController,
                backStackEntry.arguments?.getInt("age") ?: 0
            )
        }
    }
}

