package com.example.culinarapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import navigation.AppNavGraph
import presentation.ReciepeListViewModedl

class MainActivity() : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val viewModel = ReciepeListViewModedl() // Або через viewModel() якщо підключена ліба
            AppNavGraph(navController, viewModel)
        }
    }
}
