package com.example.culinarapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.culinarapp.domain.RecipeRepository
import com.example.culinarapp.domain.models.Recipe
import com.example.culinarapp.domain.usecases.GetAllRecipeUseCase
import com.example.culinarapp.domain.usecases.UpdateRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteScreenViewModel @Inject constructor(
    private val getAllRecipeUseCase: GetAllRecipeUseCase,
    private val updateRecipeUseCase: UpdateRecipeUseCase
) : ViewModel() {
    private val _favouriteRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val favouriteRecipe: StateFlow<List<Recipe>> = _favouriteRecipes

    fun loadFavouriteRecipes() {
        viewModelScope.launch {
            _favouriteRecipes.value = getAllRecipeUseCase().filter {
                it.isFavourite
            }
        }
    }


    fun toggleFavourite(recipe: Recipe) {
        val copy = recipe.copy(isFavourite = !recipe.isFavourite)
        viewModelScope.launch {
            updateRecipeUseCase(copy)
            loadFavouriteRecipes()
        }

    }

}


