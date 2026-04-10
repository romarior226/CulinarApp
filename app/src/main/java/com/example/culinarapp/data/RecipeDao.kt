package com.example.culinarapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.culinarapp.domain.models.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<RecipeDbModel>

    @Insert
    suspend fun insertExpense(recipe: RecipeDbModel): Long

    @Update
    suspend fun updateExpense(recipe: RecipeDbModel)

    @Delete
    suspend fun deleteExpense(recipe: RecipeDbModel)

}