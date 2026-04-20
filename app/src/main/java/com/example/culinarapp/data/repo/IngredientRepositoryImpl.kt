package com.example.culinarapp.data.repo

import com.example.culinarapp.data.dao.IngredientDao
import com.example.culinarapp.data.toDbModel
import com.example.culinarapp.data.toModel
import com.example.culinarapp.domain.IngredientRepository
import com.example.culinarapp.domain.models.Ingredient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IngredientRepositoryImpl @Inject constructor(
    private val ingredientDao: IngredientDao
) : IngredientRepository {
    override fun getAllIngredientListAsFlow(): Flow<Set<Ingredient>> {
        return ingredientDao.getAllIngredients().map { ingredientsDbModels ->
            ingredientsDbModels.map {
                it.toModel()
            }.toSet()
        }
    }


    override suspend fun addIngredient(ingredient: Ingredient) {
        ingredientDao.insertIngredient(ingredient.toDbModel())
    }
}

