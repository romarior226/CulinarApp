package com.example.culinarapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.culinarapp.domain.models.Recipe
import com.example.culinarapp.domain.usecases.AddRecipeUseCase
import com.example.culinarapp.domain.usecases.DeleteRecipeUseCase
import com.example.culinarapp.domain.usecases.GetAllRecipeUseCase
import com.example.culinarapp.domain.usecases.UpdateRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val addRecipeUseCase: AddRecipeUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase,
    private val getAllRecipeUseCase: GetAllRecipeUseCase,
    private val updateRecipeUseCase: UpdateRecipeUseCase

) : ViewModel() {
    private val _recipeList = MutableStateFlow<List<Recipe>>(emptyList())
    val recipeList: StateFlow<List<Recipe>> = _recipeList

    init {
        loadRecipes()
    }
    fun loadRecipes() {
        viewModelScope.launch {
            _recipeList.value = getAllRecipeUseCase()
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            deleteRecipeUseCase(recipe)
            _recipeList.value -= recipe
        }
    }

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            addRecipeUseCase(recipe)
            _recipeList.value += recipe
        }
    }
    fun updateRecipe(recipe: Recipe){
        viewModelScope.launch {
            updateRecipeUseCase(recipe)
        }
    }


    fun toggleFavourite(recipe: Recipe) {
        val copy = recipe.copy(isFavourite = !recipe.isFavourite)
        updateRecipe(copy)
        loadRecipes()
    }

}