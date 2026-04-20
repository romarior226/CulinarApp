package com.example.culinarapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.culinarapp.data.dao.IngredientDao
import com.example.culinarapp.data.dao.RecipeDao
import com.example.culinarapp.data.entity.IngredientsDbModel
import com.example.culinarapp.data.entity.RecipeDbModel

@Database(entities = [RecipeDbModel::class , IngredientsDbModel::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao

}