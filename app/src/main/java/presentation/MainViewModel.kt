package presentation

import androidx.lifecycle.ViewModel
import domain.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class MainViewModel : ViewModel() {
    val _recipeList =
        MutableStateFlow(listOf(Recipe("BORSHCH"), Recipe("PELMENI"), Recipe("KATLETKI") , Recipe("SUPCHIK") , Recipe("PISUNCHIK") , Recipe("ERIKA DANKIV") , Recipe("LIARS UNIVERSE") , Recipe("NOTE 9")))

}