package compontents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.Recipe

@Composable
fun RecipeDetailScreen(recipe: Recipe, onBackClick: () -> Unit) {
    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { onBackClick() }
            ) {
                Text("Back")
            }
            Text(
                text = "RECIEPE",
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }, modifier = Modifier.padding(vertical = 50.dp)) {
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .padding(it),
        ) {
            Spacer(modifier = Modifier.size(40.dp))
            Image(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .height(250.dp)
                    .fillMaxWidth(),
                painter = painterResource(recipe.imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.size(40.dp))

            Text(
                text = recipe.name,
                Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp

            )
            Text(
                text = recipe.category.toString(),
                Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                color = Color.Gray,
                textAlign = TextAlign.Center,
                fontSize = 20.sp

            )
            Spacer(modifier = Modifier.size(40.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .padding(horizontal = 10.dp)
            ) {
                Text(text = "Ingridients: ", fontSize = 20.sp)
                Column(Modifier.padding(horizontal = 10.dp)) {
                    recipe.ingridients.forEach { text ->
                        Text(
                            text = text,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(40.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .padding(horizontal = 10.dp)
            ) {
                Text(text = "Instruction: ", fontSize = 20.sp)
                Column(Modifier.padding(horizontal = 10.dp)) {
                    Text(
                        text = recipe.instruction,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                }
            }
        }
    }
}