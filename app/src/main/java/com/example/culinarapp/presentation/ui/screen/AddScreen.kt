package com.example.culinarapp.presentation.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.culinarapp.R
import com.example.culinarapp.domain.models.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeAddScreen(onBackClick: () -> Unit, onSaveClick: (Recipe) -> Unit) {
    var ingredients by remember { mutableStateOf(listOf<String>()) }
    var instruction by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add recipe", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }, bottomBar = {
            IconButton(
                onClick = {
                    onSaveClick(
                        Recipe(
                            name = name,
                            ingridients = ingredients,
                            instruction = instruction,
                            imageUri = selectedImageUri.toString()
                        )
                    )
                    onBackClick()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Save, contentDescription = null)
            }
        }
    ) { it ->
        Column(Modifier.padding(it)) {
            TextField(
                label = {Text("reicpe name")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                value = name,
                onValueChange = {
                    name = it
                }
            )
            RecipeAddSection(ingredients) { ingredient ->
                ingredients += ingredient
            }
            AddInstruction(instruction) {
                instruction = it
            }
            RecipeAddImage(
                selectedImageUri = selectedImageUri,
                onImageSelected = { selectedImageUri = it }
            )

        }

    }
}

@Composable
fun RecipeAddImage(
    selectedImageUri: Uri?,
    onImageSelected: (Uri) -> Unit
) {
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) onImageSelected(uri)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(10.dp)
    ) {
        if (selectedImageUri != null) {
            AsyncImage(
                model = selectedImageUri,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(R.drawable.images),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = "Chose photo",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 10.dp)
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
fun RecipeAddSection(ingredients: List<String>, onSaveClick: (String) -> Unit) {
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
                modifier = Modifier.padding(start = 10.dp)
            )
            IconButton(
                onClick = {
                    showTextField = !showTextField
                },
                Modifier
                    .padding(horizontal = 10.dp)
                    .padding(start = 220.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }

        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            if (showTextField) {
                TextField(
                    label = {Text("add ingredients")},
                    value = currentIngredient,
                    onValueChange = {
                        currentIngredient = it
                    }
                )
                IconButton(
                    onClick = {
                        onSaveClick(currentIngredient)
                    },
                    Modifier
                        .padding(horizontal = 10.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
    }
}

@Composable
fun AddInstruction(instruction: String, onSaveClick: (String) -> Unit) {
    var showTextField by remember { mutableStateOf(true) }
    var currentInstruction by remember { mutableStateOf("") }
    Column(Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Add Instruction",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 10.dp)
            )
            IconButton(
                onClick = {
                    showTextField = !showTextField
                },
                Modifier
                    .padding(horizontal = 10.dp)
                    .padding(start = 220.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }

        }
        if (showTextField) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextField(
                    label = {Text("add instruction")},
                    value = currentInstruction,
                    onValueChange = {
                        currentInstruction = it
                    }
                )
                IconButton(
                    onClick = {
                        onSaveClick(currentInstruction)
                    },
                    Modifier
                        .padding(horizontal = 10.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }

        }
    }
}

@Preview
@Composable
fun RecipeAddPreview() {
    RecipeAddScreen({}, {})
}


