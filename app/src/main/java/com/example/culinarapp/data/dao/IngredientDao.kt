package com.example.culinarapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.culinarapp.data.entity.IngredientsDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Query("SELECT * FROM ingredients ORDER BY name ASC")
    fun getAllIngredients(): Flow<List<IngredientsDbModel>>

    @Insert
    suspend fun insertIngredient(ingredient: IngredientsDbModel): Long

    @Update
    suspend fun updateIngredient(ingredient: IngredientsDbModel)

    @Delete
    suspend fun deleteIngredient(ingredient: IngredientsDbModel)

}