package com.example.culinarapp.presentation

import androidx.lifecycle.ViewModel
import com.example.culinarapp.domain.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecipeListViewModel : ViewModel() {
    private val _recipeList =
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
    val recipeList: StateFlow<List<Recipe>> = _recipeList
}