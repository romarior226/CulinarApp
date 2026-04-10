package com.example.culinarapp.domain

import com.example.culinarapp.domain.models.Recipe

interface RecipeRepository {

    suspend fun getAllRecipeList() : List<Recipe>

    suspend fun addRecipe(recipe: Recipe): Long

    suspend fun deleteRecipe(recipe: Recipe)

    suspend fun updateRecipe(recipe: Recipe)
}