package com.example.culinarapp.domain.models


data class Recipe(
    val id: Int = 0,
    val name: String,
    val category: Category = Category.TASTY,
    val imageUri: String? = null,
    val ingridients: List<String> = emptyList(),
    val instruction: String = "",
    val isFavourite: Boolean = false
)
