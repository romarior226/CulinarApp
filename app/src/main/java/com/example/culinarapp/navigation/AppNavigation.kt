package com.example.culinarapp.navigation

import com.example.culinarapp.presentation.ui.screen.RecipeDetailScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.culinarapp.presentation.EditRecipeViewModel
import com.example.culinarapp.presentation.ui.components.ColumnOfCulinaryBoards
import com.example.culinarapp.presentation.RecipeListViewModel
import com.example.culinarapp.presentation.ui.screen.RecipeAddScreen

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    recipeViewModel: RecipeListViewModel,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.ListScreen.route
    ) {
        composable(Screen.ListScreen.route) {
            val list by recipeViewModel.recipeList.collectAsStateWithLifecycle()
            ColumnOfCulinaryBoards(
                list,
                onRecipeClickListener = {
                    val route = Screen.DetailedFoodScreen.getRouteWithArgs(it.name)
                    navHostController.navigate(route)
                },
                onFavouriteClickListener = {
                    recipeViewModel.toggleFavourite(it)
                },
                onSwipeElement = {
                    recipeViewModel.deleteRecipe(it.id)
                },
                onEditClickListener = {
                    navHostController.navigate(Screen.EditScreen.getRouteWithArgs(it.id))
                }
            )

        }
        composable(Screen.AddScreen.route) {
            RecipeAddScreen(
                onBackClick = { navHostController.popBackStack() }
            )
        }
        composable(Screen.FavouriteScreen.route) {
            val favouriteList by recipeViewModel.favouriteList.collectAsStateWithLifecycle()
            ColumnOfCulinaryBoards(
                recipeList = favouriteList,
                onRecipeClickListener = {
                    val route = Screen.DetailedFoodScreen.getRouteWithArgs(it.name)
                    navHostController.navigate(route)
                },
                onFavouriteClickListener = {
                    recipeViewModel.toggleFavourite(it)
                },
                onSwipeElement = {

                },
                isSwiped = false,
                onEditClickListener = {
                    navHostController.navigate(Screen.EditScreen.getRouteWithArgs(it.id))
                }
            )
        }
        composable(
            route = Screen.DetailedFoodScreen.route,
            arguments = listOf(navArgument("recipeName") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val recipeName = navBackStackEntry.arguments?.getString("recipeName")
            val list by recipeViewModel.recipeList.collectAsState()
            val recipe = list.find { it.name == recipeName }

            if (recipe != null) {
                RecipeDetailScreen(recipe) {
                    navHostController.popBackStack()
                }
            }
        }
        composable(
            route = Screen.EditScreen.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.LongType })
        ) { navBackStackEntry ->
            val recipeId = navBackStackEntry.arguments?.getLong("recipeId")
            RecipeAddScreen(
                onBackClick = {
                    navHostController.popBackStack()
                },
                recipeId = recipeId
            )
        }
    }
}