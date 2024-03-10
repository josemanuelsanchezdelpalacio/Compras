
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam2.jose.compras.navigations.AppScreens
import com.dam2.jose.compras.screens.Comprar
import com.dam2.jose.compras.screens.FirstScreen
import com.dam2.jose.compras.screens.VerCesta

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route) {
        composable(route = AppScreens.FirstScreen.route){
            FirstScreen(navController)
        }
        composable(route = AppScreens.Comprar.route){
            Comprar(navController)
        }
        composable(route = AppScreens.VerCesta.route){
            VerCesta(navController)
        }
    }

}