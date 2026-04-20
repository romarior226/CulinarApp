package com.example.culinarapp.data

import com.example.culinarapp.data.entity.IngredientsDbModel
import com.example.culinarapp.data.entity.RecipeDbModel
import com.example.culinarapp.domain.models.Ingredient
import com.example.culinarapp.domain.models.Recipe

fun Recipe.toDbModel(): RecipeDbModel {
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

fun RecipeDbModel.toModel(): Recipe {
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

fun IngredientsDbModel.toModel(): Ingredient {
    return Ingredient(this.name)
}
fun Ingredient.toDbModel(): IngredientsDbModel {
    return IngredientsDbModel(this.name)
}