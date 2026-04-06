package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import compontents.ColumnOfCulinarBoards
import compontents.RecipeDetailScreen
import presentation.ReciepeListViewModedl

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    viewModel: ReciepeListViewModedl
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.ListScreen.route
    ) {
        composable(Screen.ListScreen.route) {
            ColumnOfCulinarBoards(
                viewModel,
                onRecipeClickListener = {
                    val route = Screen.DetailedFoodScreen.getRouteWithArgs(it.name)
                    navHostController.navigate(route)
                },
                onFavouriteClickListener = {

                }
            )

        }
        composable(
            route = Screen.DetailedFoodScreen.route,
            arguments = listOf(navArgument("recipeName") { type = NavType.StringType })
        ) {
            val recipeName = it.arguments?.getString("recipeName")
            val recipe = viewModel._recipeList.value.find {
                it.name == recipeName
            }
            if (recipe != null) {
                RecipeDetailScreen(recipe) {
                    navHostController.popBackStack()
                }
            }
        }
    }
}


