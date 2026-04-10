package com.example.culinarapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.culinarapp.R
import com.example.culinarapp.domain.models.Category

@Entity(tableName = "recipes")
data class RecipeDbModel(
    @PrimaryKey(true) val id: Int = 0,
    val name: String,
    val category: Category,
    val imageId: Int,
    val ingridients: List<String>,
    val instruction: String,
    val isFavourite: Boolean = false
)