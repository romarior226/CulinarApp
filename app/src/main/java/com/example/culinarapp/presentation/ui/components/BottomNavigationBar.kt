package com.example.culinarapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.culinarapp.navigation.Screen

@Composable
fun BottomBar(
    currentRoute: String,
    onItemClickListener: (String) -> Unit
) {
    val items = listOf(
        NavItems.Home,
        NavItems.Add,
        NavItems.Favourite,
    )
    NavigationBar(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()

    ) {
        items.forEach { item ->
            val selected = currentRoute == item.screen.route
            NavigationBarItem(
                modifier = Modifier.height(60.dp),
                selected = selected,
                onClick = {
                    onItemClickListener(item.screen.route)
                },
                icon = {
                    Image(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = item.title
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomBarPreview(
) {
    BottomBar(
        currentRoute = Screen.FavouriteScreen.route
    ) { }
}