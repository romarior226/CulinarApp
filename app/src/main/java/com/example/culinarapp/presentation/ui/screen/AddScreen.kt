package com.example.culinarapp.presentation.ui.screen


import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.culinarapp.R
import com.example.culinarapp.presentation.EditRecipeViewModel
import com.example.culinarapp.presentation.ui.theme.veryLightRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeAddScreen(
    onBackClick: () -> Unit,
    recipeId: Long? = null,
    viewModel: EditRecipeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(recipeId) {
        if (recipeId != null && recipeId != -1L) {
            viewModel.loadRecipe(recipeId)
        }
    }

    LaunchedEffect(uiState.isSaved) {
        if (uiState.isSaved) onBackClick()
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        if (uiState.isEditMode) "Edit recipe" else "Add recipe",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "null"
                        )
                    }
                }
            )
        }, bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,

                ) {
                if (uiState.isEditMode) {
                    IconButton(
                        onClick = {
                            uiState.id?.let {
                                viewModel.deleteRecipe(it)
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(bottom = 20.dp)
                    ) {
                        Icon(Icons.Default.Clear, contentDescription = null)
                    }
                }
                IconButton(
                    onClick = {
                        viewModel.saveRecipe()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 20.dp)
                ) {
                    Icon(Icons.Default.Save, contentDescription = null)
                }
            }
        }
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            TextField(
                value = uiState.name,
                label = { Text("name") },
                onValueChange = { viewModel.updateName(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .clip(shape = RoundedCornerShape(30)),

                )
            RecipeAddSection(
                ingredients = uiState.ingredients,
                onAddIngredient = {
                    viewModel.addIngredient(it)
                },
                onRemoveIngredient = {
                    viewModel.removeIngredient(it)
                }
            )
            AddInstruction(uiState.instruction) {
                viewModel.updateInstruction(it)
            }
            RecipeAddImage(
                selectedImageUri = uiState.imageUri,
                onImageSelected = { viewModel.updateImage(it) }
            )

        }

    }
}

@Composable
fun RecipeAddImage(
    selectedImageUri: Uri?,
    onImageSelected: (Uri) -> Unit
) {
    val context = LocalContext.current

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            // зберігаємо постійний доступ
            context.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            onImageSelected(uri)
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth()
    ) {
        if (selectedImageUri != null) {
            AsyncImage(
                model = selectedImageUri,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(80.dp)
                    .clip(RoundedCornerShape(30)),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(R.drawable.images),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(80.dp)
                    .clip(RoundedCornerShape(30)),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = "Chose photo",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(1f),
            textAlign = TextAlign.Center
        )
        IconButton(
            onClick = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}

@Composable
fun RecipeAddSection(
    ingredients: List<String>,
    onAddIngredient: (String) -> Unit,
    onRemoveIngredient: (String) -> Unit
) {
    var showTextField by remember { mutableStateOf(true) }
    var currentIngredient by remember { mutableStateOf("") }
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Add Ingiridend",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f)
            )
            IconButton(
                onClick = {
                    showTextField = !showTextField
                },
                Modifier
                    .padding(horizontal = 10.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }

        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showTextField) {
                TextField(
                    label = { Text("add ingredients") },
                    value = currentIngredient,
                    onValueChange = {
                        currentIngredient = it
                    },
                    modifier = Modifier
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(30))
                )
                IconButton(
                    onClick = {
                        onAddIngredient(currentIngredient)
                        currentIngredient = ""
                    },
                    Modifier
                        .padding(horizontal = 10.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }

            }
        }
        Ingredients(ingredients, onDeleteClick = onRemoveIngredient)

    }
}

@Composable
fun Ingredients(ingredients: List<String>, onDeleteClick: (String) -> Unit) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        ingredients.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(color = veryLightRed, shape = RoundedCornerShape(30))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 10.dp),
                    fontSize = 12.sp,
                    text = item
                )
                Spacer(Modifier.width(10.dp))
                IconButton(
                    modifier = Modifier.size(14.dp),
                    onClick = {
                        onDeleteClick(item)
                    }
                ) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                }
            }

        }
    }
}

@Composable
fun AddInstruction(instruction: String, onSaveClick: (String) -> Unit) {
    var showTextField by remember { mutableStateOf(true) }
    var currentInstruction by remember(instruction) {
        mutableStateOf(instruction)
    }
    var isSaved by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Add Instruction",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f)
            )
            IconButton(
                onClick = {
                    showTextField = !showTextField
                },
                Modifier
                    .padding(horizontal = 10.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }

        }
        if (showTextField) {
            Row(
                Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    label = { Text("add instruction") },
                    value = currentInstruction,
                    onValueChange = {
                        currentInstruction = it
                        isSaved = false
                    },
                    modifier = Modifier
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(30))
                )
                IconButton(
                    onClick = {
                        onSaveClick(currentInstruction)
                        isSaved = true
                    },
                    Modifier
                        .padding(horizontal = 10.dp)
                ) {
                    Icon(
                        imageVector = if (isSaved) Icons.Default.Check else Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientsPreview() {
    Ingredients(listOf("test1 ", "test2", "test3"), {})
}

@Preview
@Composable
fun RecipeAddPreview() {
    RecipeAddScreen(
        onBackClick = {},
        recipeId = 10,
    )
}

