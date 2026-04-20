package com.example.culinarapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.culinarapp.data.entity.RecipeDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<RecipeDbModel>>

    @Insert
    suspend fun insertRecipe(recipe: RecipeDbModel): Long

    @Update
    suspend fun updateRecipe(recipe: RecipeDbModel)

    @Query("DELETE FROM recipes WHERE id = :recipeId")
    suspend fun deleteRecipe(recipeId: Long)

    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun getRecipeById(id: Long): RecipeDbModel

}