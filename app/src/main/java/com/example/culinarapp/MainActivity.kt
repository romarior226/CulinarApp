package com.example.culinarapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.culinarapp.ui.screen.MainScreen
import com.example.culinarapp.presentation.FavouriteScreenViewModel
import com.example.culinarapp.presentation.RecipeListViewModel


class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val recipeViewModel: RecipeListViewModel = viewModel()
            val favouriteViewModel: FavouriteScreenViewModel = viewModel()
            MainScreen(recipeViewModel, favouriteViewModel)

        }
    }
}
