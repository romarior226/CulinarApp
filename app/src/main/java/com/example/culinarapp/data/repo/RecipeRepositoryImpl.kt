package com.example.culinarapp.data.repo

import android.content.Context
import android.net.Uri
import com.example.culinarapp.data.dao.RecipeDao
import com.example.culinarapp.data.toDbModel
import com.example.culinarapp.data.toModel
import com.example.culinarapp.domain.RecipeRepository
import com.example.culinarapp.domain.models.Recipe
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    @ApplicationContext private val context: Context
) : RecipeRepository {

    override suspend fun getRecipeById(id: Long): Recipe {
        return recipeDao.getRecipeById(id).toModel()
    }

    override fun getAllRecipeListAsFlow(): Flow<List<Recipe>> {
        return recipeDao.getAllRecipes().map { list ->
            list.map {
                it.toModel()
            }
        }
    }

    // ОСЬ ТУТ має бути логіка збереження з картинкою!
    override suspend fun addRecipe(recipe: Recipe): Long {
        // 1. Беремо URI, який прийшов з екрана, і копіюємо файл у пам'ять додатку
        val permanentImagePath = copyImageToApp(recipe.imageUri)

        // 2. Створюємо копію рецепта, але замінюємо тимчасовий URI на постійний шлях
        val recipeToSave = recipe.copy(imageUri = permanentImagePath)

        // 3. ТІЛЬКИ ТЕПЕР викликаємо твій мапер і пишемо в базу!
        return recipeDao.insertRecipe(recipeToSave.toDbModel())
    }

    override suspend fun deleteRecipe(recipeID: Long) {
        recipeDao.deleteRecipe(recipeID)
    }

    override suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.updateRecipe(recipe.toDbModel())
    }

    // А ОСЬ ТУТ - сама функція, яка фізично копіює файл (щоб картинка не зникала)
    private suspend fun copyImageToApp(uriString: String?): String? {
        if (uriString == null) return null

        return withContext(Dispatchers.IO) {
            try {
                val uri = Uri.parse(uriString)
                val inputStream = context.contentResolver.openInputStream(uri) ?: return@withContext null
                val fileName = "recipe_${System.currentTimeMillis()}.jpg"
                val file = File(context.filesDir, fileName)
                val outputStream = FileOutputStream(file)

                inputStream.copyTo(outputStream)

                inputStream.close()
                outputStream.close()

                file.absolutePath // Повертаємо ШЛЯХ до нашої нової картинки
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}