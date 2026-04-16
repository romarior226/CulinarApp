package com.example.culinarapp.di

import android.content.Context
import com.example.culinarapp.data.AppDataBase
import com.example.culinarapp.data.RecipeDao
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
        return AppDataBase.getDatabase(context)
    }

    @Provides
    fun providesRecipeDao(database: AppDataBase): RecipeDao {
        return database.recipeDao()
    }
}