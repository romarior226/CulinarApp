package com.example.culinarapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.culinarapp.domain.Recipe


@Composable
fun ColumnOfCulinaryBoards(
    recipeList: List<Recipe>,
    onRecipeClickListener: (Recipe) -> Unit,
    onFavouriteClickListener: (Recipe) -> Unit
) {
    LazyColumn(modifier = Modifier.padding()) {
        items(recipeList) { recipe ->
            CulinaryBoard(recipe, onRecipeClickListener, onFavouriteClickListener)
        }
    }

}

@Preview
@Composable
fun PreviewBoards() {
    val recipeList = listOf(
        Recipe(name = "Test1"),
        Recipe(name = "Test2"),
        Recipe(name = "Test3"),
        Recipe(name = "Test4")

    )
    LazyColumn(modifier = Modifier.padding()) {
        items(recipeList) { recipe ->
            CulinaryBoard(recipe, {}, { })
        }
    }
}