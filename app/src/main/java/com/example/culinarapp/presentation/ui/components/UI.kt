package com.example.culinarapp.presentation.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.culinarapp.domain.models.Recipe
import com.example.culinarapp.presentation.ui.theme.lightRed


@Composable
fun ColumnOfCulinaryBoards(
    recipeList: List<Recipe>,
    onRecipeClickListener: (Recipe) -> Unit,
    onFavouriteClickListener: (Recipe) -> Unit,
    onEditClickListener: (Recipe) -> Unit,
    isSwiped: Boolean = true,
    onSwipeElement: (Recipe) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding() // Додає відступ тільки зверху статус-бару
    ) {

        LazyColumn(modifier = Modifier.padding()) {
            items(items = recipeList, key = { it.id }) { recipe ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = {
                        when (it) {
                            SwipeToDismissBoxValue.StartToEnd -> false
                            SwipeToDismissBoxValue.EndToStart -> {
                                onSwipeElement(recipe)
                                true
                            }

                            SwipeToDismissBoxValue.Settled -> false
                        }
                    },
//                    positionalThreshold = {
//                        it * 0.60f
//                    }
                )
                SwipeToDismissBox(
                    modifier = Modifier.animateItem(
                        fadeOutSpec = tween(500)
                    ),
                    enableDismissFromStartToEnd = false,
                    enableDismissFromEndToStart = isSwiped,
                    state = dismissState,
                    backgroundContent = {
                        val color by
                        animateColorAsState(
                            when (dismissState.targetValue) {
                                SwipeToDismissBoxValue.Settled -> Color.LightGray
                                SwipeToDismissBoxValue.StartToEnd -> Color.Green
                                SwipeToDismissBoxValue.EndToStart -> lightRed
                            }
                        )
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(color)
                        )
                    },
                )
                {
                    CulinaryBoard(
                        recipe,
                        onRecipeClickListener,
                        onFavouriteClickListener,
                        onEditClickListener
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewBoards() {
    ColumnOfCulinaryBoards(
        recipeList = listOf(
            Recipe(
                name = "Test1"
            ),
            Recipe(
                name = "Test2"
            ),
            Recipe(
                name = "Test3"
            )
        ),
        onRecipeClickListener = {},
        onFavouriteClickListener = { },
        isSwiped = true,
        onEditClickListener = {},
        onSwipeElement = {},
    )
}