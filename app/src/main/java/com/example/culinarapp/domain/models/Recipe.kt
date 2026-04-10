package com.example.culinarapp.domain.models

import android.net.Uri
import com.example.culinarapp.R

data class Recipe(
    val id: Int = 0,
    val name: String,
    val category: Category = Category.TASTY,
    val imageId: Int = R.drawable.images,
    val imageUri: String? = null,
    val ingridients: List<String> = listOf("Nigga1", "Nigga2", "Nigga3", "Nigga5", "Nigga5"),
    val instruction: String = "Nigga instruction",
    val isFavourite: Boolean = false
)
