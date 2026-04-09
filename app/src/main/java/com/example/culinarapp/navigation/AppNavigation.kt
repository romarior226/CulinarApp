package com.example.culinarapp.navigation

import RecipeDetailScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.culinarapp.presentation.ui.components.ColumnOfCulinaryBoards
import com.example.culinarapp.presentation.FavouriteScreenViewModel
import com.example.culinarapp.presentation.RecipeListViewModel

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    recipeViewModel: RecipeListViewModel,
    favouriteViewModel: FavouriteScreenViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.ListScreen.route
    ) {
        composable(Screen.ListScreen.route) {
            val list by recipeViewModel.recipeList.collectAsState()
            ColumnOfCulinaryBoards(
                list,
                onRecipeClickListener = {
                    val route = Screen.DetailedFoodScreen.getRouteWithArgs(it.name)
                    navHostController.navigate(route)
                },
                onFavouriteClickListener = {
                    recipeViewModel.toggleFavourite(it)
                }
            )

        }
        composable(Screen.FavouriteScreen.route) {
            val favouriteList by favouriteViewModel.favouriteRecipes.collectAsState()
            ColumnOfCulinaryBoards(
                recipeList = favouriteList,
                onRecipeClickListener = {
                    val route = Screen.DetailedFoodScreen.getRouteWithArgs(it.name)
                    navHostController.navigate(route)
                },
                onFavouriteClickListener = {
                    recipeViewModel.toggleFavourite(it)
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
    }
}