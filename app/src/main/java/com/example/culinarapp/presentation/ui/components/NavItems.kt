package com.example.culinarapp.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.culinarapp.R
import com.example.culinarapp.navigation.Screen

sealed class NavItems(
    val screen: Screen,
    val title: String,
    val icon: ImageVector
) {
    object Home : NavItems(
        screen = Screen.ListScreen,
        title = "Рецепти",
        icon = Icons.Default.Home
    )
    object Favourite : NavItems(
        screen = Screen.FavouriteScreen,
        title = "Улюблені",
        icon = Icons.Default.Favorite
    )
    object Add : NavItems(
        screen = Screen.AddScreen,
        title = "Додати",
        icon = Icons.Default.AddCircleOutline
    )
}