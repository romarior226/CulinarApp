package com.example.culinarapp.data

import com.example.culinarapp.domain.models.Recipe

fun Recipe.toDbModel() : RecipeDbModel{
    return RecipeDbModel(
        id = this.id,
        name = this.name,
        category = this.category,
        imagePath = this.imageUri,
        ingridients = this.ingridients,
        instruction = this.instruction,
        isFavourite = this.isFavourite
    )
}
fun RecipeDbModel.toEntity() : Recipe{
    return Recipe(
        id = this.id,
        name = this.name,
        category = this.category,
        imageUri = this.imagePath,
        ingridients = this.ingridients,
        instruction = this.instruction,
        isFavourite = this.isFavourite
    )
}