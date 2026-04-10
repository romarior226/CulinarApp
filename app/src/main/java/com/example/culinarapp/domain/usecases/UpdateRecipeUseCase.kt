package com.example.culinarapp.domain.usecases

import com.example.culinarapp.domain.RecipeRepository
import com.example.culinarapp.domain.models.Recipe
import javax.inject.Inject

class UpdateRecipeUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(recipe: Recipe) {
        return recipeRepository.updateRecipe(recipe)
    }
}