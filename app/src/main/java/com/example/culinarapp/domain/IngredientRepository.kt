package com.example.culinarapp.domain

import com.example.culinarapp.domain.models.Ingredient
import com.example.culinarapp.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

interface IngredientRepository {

    fun getAllIngredientListAsFlow() : Flow<Set<Ingredient>>

    suspend fun addIngredient(ingredient: Ingredient)
}