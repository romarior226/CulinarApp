package com.example.culinarapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.culinarapp.domain.models.Recipe
import com.example.culinarapp.domain.usecases.AddRecipeUseCase
import com.example.culinarapp.domain.usecases.DeleteRecipeUseCase
import com.example.culinarapp.domain.usecases.GetAllRecipeUseCase
import com.example.culinarapp.domain.usecases.UpdateRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val addRecipeUseCase: AddRecipeUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase,
    private val getAllRecipeUseCase: GetAllRecipeUseCase,
    private val updateRecipeUseCase: UpdateRecipeUseCase

) : ViewModel() {
    val recipeList: StateFlow<List<Recipe>> = getAllRecipeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val favouriteList: StateFlow<List<Recipe>> = recipeList
        .map { list -> list.filter { it.isFavourite } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            deleteRecipeUseCase(recipe)
        }
    }

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            addRecipeUseCase(recipe)
        }
    }

    fun updateRecipe(recipe: Recipe) {
        viewModelScope.launch {
            updateRecipeUseCase(recipe)
        }
    }


    fun toggleFavourite(recipe: Recipe) {
        viewModelScope.launch {
            updateRecipe(recipe.copy(isFavourite = !recipe.isFavourite))
        }
    }

}