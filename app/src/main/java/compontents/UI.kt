package compontents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.culinarapp.R
import domain.Recipe
import presentation.ReciepeListViewModedl

@Composable
fun CulinarBoard(
    recipe: Recipe,
    onRecipeClickListener: (Recipe) -> Unit,
    onFavouriteClickListener: (Recipe) -> Unit
) {
    val context = LocalContext.current.applicationContext
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
            Image(
                painter = painterResource(id = recipe.imageId),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(80.dp),
                contentScale = ContentScale.Crop

            )
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
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ColumnOfCulinarBoards(
    viewModel: ReciepeListViewModedl,
    onRecipeClickListener: (Recipe) -> Unit,
    onFavouriteClickListener: (Recipe) -> Unit
) {
    val list by viewModel._recipeList.collectAsState(emptyList())
    LazyColumn(modifier = Modifier.padding(vertical = 25.dp)) {
        items(list) { recipe ->
            CulinarBoard(recipe, onRecipeClickListener, onFavouriteClickListener)
        }
    }
}