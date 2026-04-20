package com.example.culinarapp.navigation

sealed class Screen(
    val route: String
) {
    object ListScreen : Screen(LIST_SCREEN)

    object AddScreen : Screen(ADD_SCREEN)

    object FavouriteScreen : Screen(FAVOURITE_SCREEN)
    object DetailedFoodScreen : Screen(DETAILED_SCREEN) {
        const val ROUTE_FOR_ARGS = "detailed_screen"
        fun getRouteWithArgs(recipeName: String): String {
            return "$ROUTE_FOR_ARGS/$recipeName"

        }
    }
    object EditScreen : Screen(EDIT_SCREEN) {
        const val ROUTE_FOR_ARGS = "edit_screen"

        fun getRouteWithArgs(recipeId : Long): String {
            return "$ROUTE_FOR_ARGS/$recipeId"
        }
    }

    companion object {

        const val ADD_SCREEN = "adding_screen"
        const val FAVOURITE_SCREEN = "favourite_screen"
        const val LIST_SCREEN = "list_screen"
        const val DETAILED_SCREEN = "detailed_screen/{recipeName}"

        const val EDIT_SCREEN = "edit_screen/{recipeId}"
    }
}
