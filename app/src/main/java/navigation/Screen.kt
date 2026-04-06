package navigation

import android.net.Uri
import com.google.gson.Gson
import domain.Recipe

sealed class Screen(
    val route: String
) {
    object ListScreen : Screen(LIST_SCREEN)
    object DetailedFoodScreen : Screen(DETAILED_SCREEN) {
        val ROUTE_FOR_ARGS = "detailed_screen"
        fun getRouteWithArgs(recipeName: String): String {
            return "$ROUTE_FOR_ARGS/$recipeName"

        }
    }

    companion object {
        const val LIST_SCREEN = "list_screen"
        const val DETAILED_SCREEN = "detailed_screen/{recipeName}"
    }
}
