package presentation

import androidx.lifecycle.ViewModel
import domain.Recipe
import kotlinx.coroutines.flow.MutableStateFlow

class ReciepeListViewModedl : ViewModel() {
    val _recipeList =
        MutableStateFlow(
            listOf(
                Recipe(
                    "BORSCH",
                    instruction = "ITS BORSCH",
                    ingridients = listOf("voda", "ovochi", "miasko")
                ),
                Recipe(
                    "PELMENI",
                    instruction = "ITS PELMENI",
                    ingridients = listOf("tisto", "miaso", "i vse")
                ),
                Recipe(
                    "KATLETKI",
                    instruction = "ITS KATLETKI",
                    ingridients = listOf("miaso", "tsibula", "i vse")
                ),
                Recipe(
                    "SUPCHIK",
                    instruction = "ITS SUPCHIK",
                    ingridients = listOf("voda", "ovochi", "i vse")
                ),
            )
        )
}