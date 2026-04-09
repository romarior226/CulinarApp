package com.example.culinarapp.presentation

import androidx.lifecycle.ViewModel
import com.example.culinarapp.domain.models.Recipe

class RecipeListViewModel : ViewModel() {
    val recipeList = MockRepo.recipes

    fun toggleFavourite(recipe: Recipe) {
        MockRepo.toggleFavourite(recipe.name)
    }
}