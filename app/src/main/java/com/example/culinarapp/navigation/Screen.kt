package com.example.culinarapp.navigation

sealed class Screen(
    val route: String
) {
    object ListScreen : Screen(LIST_SCREEN)

    object FavouriteScreen : Screen(FAVOURITE_SCREEN)
    object DetailedFoodScreen : Screen(DETAILED_SCREEN) {
        const val ROUTE_FOR_ARGS = "detailed_screen"
        fun getRouteWithArgs(recipeName: String): String {
            return "$ROUTE_FOR_ARGS/$recipeName"

        }
    }

    companion object {

        const val FAVOURITE_SCREEN = "favourite_screen"
        const val LIST_SCREEN = "list_screen"
        const val DETAILED_SCREEN = "detailed_screen/{recipeName}"
    }
}
