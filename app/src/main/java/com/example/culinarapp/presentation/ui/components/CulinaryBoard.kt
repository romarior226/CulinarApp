package com.example.culinarapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.culinarapp.domain.models.Recipe


@Composable
fun CulinaryBoard(
    recipe: Recipe,
    onRecipeClickListener: (Recipe) -> Unit,
    onFavouriteClickListener: (Recipe) -> Unit
) {
    val imageUri = recipe.imageUri
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable(
                enabled = true,
                interactionSource = null,
                indication = null,
                onClick = {
                    onRecipeClickListener(recipe)
                }
            )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (imageUri != null) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = recipe.imageId),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(80.dp)
                        .clip(shape = RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center

            ) {
                Text(
                    recipe.name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )
                Text(
                    text = recipe.category.toString(),
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )
            }
            IconButton(
                onClick = {
                    onFavouriteClickListener(recipe)
                },
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                val currentsIconState = if (recipe.isFavourite) Icons.Default.Favorite
                else Icons.Default.FavoriteBorder
                Icon(
                    imageVector = currentsIconState,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    CulinaryBoard(
        recipe = Recipe(name = "Test"),
        onRecipeClickListener = {},
        onFavouriteClickListener = {}
    )
}