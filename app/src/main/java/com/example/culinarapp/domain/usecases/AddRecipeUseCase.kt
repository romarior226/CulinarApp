package com.example.culinarapp.domain.usecases

import com.example.culinarapp.domain.RecipeRepository
import com.example.culinarapp.domain.models.Recipe
import javax.inject.Inject

class AddRecipeUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(recipe: Recipe): Recipe {
        val generatedId = recipeRepository.addRecipe(recipe)
        return recipe.copy(id = generatedId)
    }
}