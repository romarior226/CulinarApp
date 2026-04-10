//package com.example.culinarapp.presentation
//
//import com.example.culinarapp.domain.models.Recipe
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//
//object MockRepo   {
//    private val _recipes = MutableStateFlow(
//        listOf(
//            Recipe( name = "Борщ український",  ),
//            Recipe( name = "Паста Карбонара",  ),
//            Recipe( name = "Сирники",  ),
//            Recipe( name = "Салат Цезар",  )
//        )
//    )
//
//    val recipes: StateFlow<List<Recipe>> = _recipes
//
//    fun toggleFavourite(recipeName: String) {
//        _recipes.value = _recipes.value.map { recipe ->
//            if (recipe.name == recipeName) {
//                recipe.copy(isFavourite = !recipe.isFavourite)
//            } else {
//                recipe
//            }
//        }
//    }
//}