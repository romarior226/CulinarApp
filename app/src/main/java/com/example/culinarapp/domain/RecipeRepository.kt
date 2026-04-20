package com.example.culinarapp.domain

import com.example.culinarapp.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getRecipeById(id: Long): Recipe
    fun getAllRecipeListAsFlow() : Flow<List<Recipe>>

    suspend fun addRecipe(recipe: Recipe): Long

    suspend fun deleteRecipe(recipeID: Long)

    suspend fun updateRecipe(recipe: Recipe)
}