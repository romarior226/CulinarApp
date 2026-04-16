//package com.example.culinarapp.presentation
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.culinarapp.domain.models.Recipe
//import com.example.culinarapp.domain.usecases.GetAllRecipeUseCase
//import com.example.culinarapp.domain.usecases.UpdateRecipeUseCase
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class FavouriteScreenViewModel @Inject constructor(
//    private val getAllRecipeUseCase: GetAllRecipeUseCase,
//    private val updateRecipeUseCase: UpdateRecipeUseCase
//) : ViewModel() {
//
//    init {
//        loadFavouriteRecipes()
//    }
//
//    private val _favouriteRecipes = MutableStateFlow<List<Recipe>>(emptyList())
//    val favouriteRecipe: StateFlow<List<Recipe>> = _favouriteRecipes
//
//    fun loadFavouriteRecipes() {
//        viewModelScope.launch {
//            if (getAllRecipeUseCase() != null) {
//                _favouriteRecipes.value = getAllRecipeUseCase().filter {
//                    it.isFavourite
//                }
//            }
//        }
//    }
//
//
//    fun toggleFavourite(recipe: Recipe) {
//        viewModelScope.launch {
//            _favouriteRecipes.value = _favouriteRecipes.value.map {
//                if (it.id == recipe.id) {
//                    it.copy(isFavourite = !it.isFavourite)
//                } else it
//            }
//            updateRecipeUseCase(recipe.copy(isFavourite = !recipe.isFavourite))
//        }
//    }
//
//}


