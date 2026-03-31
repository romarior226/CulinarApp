package com.example.culinarapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.culinarapp.ui.theme.CulinarAppTheme
import compontents.ColumnOfCulinarBoards
import presentation.MainViewModel

class MainActivity : ComponentActivity() {
    val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CulinarAppTheme {
                ColumnOfCulinarBoards(viewModel)
            }
        }
    }
}
