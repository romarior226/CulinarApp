package com.example.culinarapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ingredients")
data class IngredientsDbModel(
    @PrimaryKey
    val name: String
)