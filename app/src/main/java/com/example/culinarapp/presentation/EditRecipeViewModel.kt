package com.example.culinarapp.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.culinarapp.domain.models.Recipe
import com.example.culinarapp.domain.usecases.AddRecipeUseCase
import com.example.culinarapp.domain.usecases.DeleteRecipeUseCase
import com.example.culinarapp.domain.usecases.GetRecipeByIdUseCase
import com.example.culinarapp.domain.usecases.UpdateRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RecipeUiState(
    val id: Long? = null,
    val name: String = "",
    val ingredients: List<String> = emptyList(),
    val instruction: String = "",
    val imageUri: Uri? = null,
    val isSaved: Boolean = false // щоб закрити екран після успішного збереження
) {
    val isEditMode: Boolean get() = id != null
    val isDataValid: Boolean get() = name.isNotBlank() && ingredients.isNotEmpty() && instruction.isNotBlank()
}

@HiltViewModel
class EditRecipeViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    private val addRecipeUseCase: AddRecipeUseCase,
    private val updateRecipeUseCase: UpdateRecipeUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(RecipeUiState())
    val state = _state.asStateFlow()
    fun loadRecipe(id: Long) {
        viewModelScope.launch {
            val recipe = getRecipeByIdUseCase(id)
            _state.update {
                it.copy(
                    id = recipe.id,
                    name = recipe.name,
                    ingredients = recipe.ingridients,
                    instruction = recipe.instruction,
                    imageUri = recipe.imageUri?.let { Uri.parse(it) }
                )
            }
        }
    }

    fun saveRecipe() {
        viewModelScope.launch {
            val currentState = _state.value
            val recipe = Recipe(
                id = currentState.id ?: 0,
                name = currentState.name,
                ingridients = currentState.ingredients,
                instruction = currentState.instruction,
                imageUri = currentState.imageUri?.toString()
            )

            if (currentState.isEditMode) {
                updateRecipeUseCase(recipe)
            } else {
                addRecipeUseCase(recipe)
            }
            _state.update { it.copy(isSaved = true) }
        }
    }

    fun updateName(newName: String) {
        _state.update { it.copy(name = newName) }
    }

    fun updateInstruction(newInstruction: String) {
        _state.update { it.copy(instruction = newInstruction) }
    }

    fun updateImage(newUri: Uri?) {
        _state.update { it.copy(imageUri = newUri) }
    }

    fun addIngredient(ingredient: String) {
        if (ingredient.isNotBlank()) {
            _state.update { it.copy(ingredients = it.ingredients + ingredient) }
        }
    }

    fun removeIngredient(ingredient: String) {
        _state.update { it.copy(ingredients = it.ingredients - ingredient) }
    }

    fun deleteRecipe(recipeId : Long) {
        viewModelScope.launch {
            deleteRecipeUseCase(recipeId)
            _state.update { it.copy(isSaved = true) }
        }
    }
}