package com.example.culinarapp.domain.usecases

import com.example.culinarapp.domain.RecipeRepository
import com.example.culinarapp.domain.models.Recipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllRecipeUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    operator fun invoke(): Flow<List<Recipe>> {
        return recipeRepository.getAllRecipeListAsFlow()
    }

}