package com.example.culinarapp.domain.usecases

import com.example.culinarapp.domain.RecipeRepository
import com.example.culinarapp.domain.models.Recipe
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: Long): Recipe {
        return repository.getRecipeById(id)
    }
}
