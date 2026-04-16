package com.example.culinarapp.domain

import com.example.culinarapp.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getAllRecipeListAsFlow() : Flow<List<Recipe>>

    suspend fun addRecipe(recipe: Recipe): Long

    suspend fun deleteRecipe(recipe: Recipe)

    suspend fun updateRecipe(recipe: Recipe)
}