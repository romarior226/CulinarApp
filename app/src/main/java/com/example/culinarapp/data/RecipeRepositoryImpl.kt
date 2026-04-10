package com.example.culinarapp.data

import com.example.culinarapp.domain.RecipeRepository
import com.example.culinarapp.domain.models.Recipe
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(private val recipeDao: RecipeDao) :
    RecipeRepository {
    suspend fun getAllRecipeDbModelList(): List<RecipeDbModel> {
        return recipeDao.getAllRecipes()
    }

    override suspend fun getAllRecipeList(): List<Recipe> {
        return recipeDao.getAllRecipes().map {
            it.toEntity()
        }
    }

    override suspend fun addRecipe(recipe: Recipe): Long {
        return recipeDao.insertExpense(recipe.toDbModel())
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipeDao.deleteExpense(recipe.toDbModel())
    }

    override suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.updateExpense(recipe.toDbModel())
    }
}