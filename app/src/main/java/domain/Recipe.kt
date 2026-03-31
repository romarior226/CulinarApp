package domain

import com.example.culinarapp.R

data class Recipe(
    val name: String,
    val category: Category = Category.TASTY,
    val imageId: Int = R.drawable.ic_food_background,
    val ingridients: List<String> = listOf(),
    val instruction: String = ""
)
