package com.example.culinarapp.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.culinarapp.navigation.AppNavGraph
import com.example.culinarapp.navigation.Screen
import com.example.culinarapp.presentation.RecipeListViewModel
import com.example.culinarapp.presentation.ui.components.BottomBar

@Composable
fun MainScreen(
    recipeViewModel: RecipeListViewModel,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == Screen.ListScreen.route || currentRoute == Screen.FavouriteScreen.route ) {
                BottomBar(
                    currentRoute = currentRoute ?: Screen.ListScreen.route,
                    onItemClickListener = {
                        navController.navigate(route = it) {
                            popUpTo(Screen.ListScreen.route) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(
                bottom = paddingValues.calculateBottomPadding() // Відступаємо тільки знизу (для BottomBar)
            )
        ) {
            AppNavGraph(
                navHostController = navController,
                recipeViewModel = recipeViewModel
            )
        }
    }
}