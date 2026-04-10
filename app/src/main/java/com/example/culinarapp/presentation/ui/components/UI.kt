package com.example.culinarapp.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.culinarapp.domain.models.Category
import com.example.culinarapp.domain.models.Recipe


@Composable
fun ColumnOfCulinaryBoards(
    recipeList: List<Recipe>,
    onRecipeClickListener: (Recipe) -> Unit,
    onFavouriteClickListener: (Recipe) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding() // Додає відступ тільки зверху статус-бару
    ) {
        LazyColumn(modifier = Modifier.padding()) {
            items(recipeList) { recipe ->
                CulinaryBoard(recipe, onRecipeClickListener, onFavouriteClickListener)
            }
        }
    }

}

@Preview
@Composable
fun PreviewBoards() {
    ColumnOfCulinaryBoards(
        recipeList = listOf(Recipe(
            1,
            name = "text",
            category = Category.TASTY,
            imageId = TODO() ,
            ingridients = TODO(),
            instruction = TODO(),
            isFavourite = TODO()
        )),
        onRecipeClickListener = {},
        onFavouriteClickListener = {}
    )
}