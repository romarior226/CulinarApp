package com.example.culinarapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.culinarapp.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<RecipeDbModel>>

    @Insert
    suspend fun insertExpense(recipe: RecipeDbModel): Long

    @Update
    suspend fun updateExpense(recipe: RecipeDbModel)

    @Delete
    suspend fun deleteExpense(recipe: RecipeDbModel)

}