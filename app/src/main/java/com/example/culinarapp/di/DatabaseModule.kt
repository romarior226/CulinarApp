package com.example.culinarapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.culinarapp.data.AppDataBase
import com.example.culinarapp.data.dao.IngredientDao
import com.example.culinarapp.data.dao.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDataBase::class.java,
            name = "recipeDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesRecipeDao(database: AppDataBase): RecipeDao {
        return database.recipeDao()
    }
    @Provides
    @Singleton
    fun providesIngredientDao(database: AppDataBase) : IngredientDao {
        return database.ingredientDao()
    }
}