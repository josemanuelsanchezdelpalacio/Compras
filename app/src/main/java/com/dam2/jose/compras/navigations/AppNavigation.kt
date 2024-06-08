
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam2.jose.compras.models.ViewModel
import com.dam2.jose.compras.navigations.AppScreens
import com.dam2.jose.compras.screens.Comprar
import com.dam2.jose.compras.screens.FirstScreen
import com.dam2.jose.compras.screens.VerCesta

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val mvvm = remember { ViewModel() }

    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route) {
        composable(route = AppScreens.FirstScreen.route) {
            FirstScreen(navController, mvvm)
        }
        composable(route = AppScreens.Comprar.route) {
            Comprar(navController, mvvm)
        }
        composable(route = AppScreens.VerCesta.route) {
            VerCesta(navController, mvvm)
        }
    }
}