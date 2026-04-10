package com.example.culinarapp.data


import androidx.room.TypeConverter
import com.example.culinarapp.domain.models.Category

class Converters {
    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toList(string: String): List<String> {
        return string.split(",")
    }

    @TypeConverter
    fun fromCategory(category: Category): String {
        return category.name
    }

    @TypeConverter
    fun toCategory(string: String): Category {
        return Category.valueOf(string)

    }
}