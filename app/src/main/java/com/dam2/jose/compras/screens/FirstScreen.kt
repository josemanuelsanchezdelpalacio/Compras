package com.dam2.jose.compras.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dam2.jose.compras.data.listaComprado
import com.dam2.jose.compras.models.ViewModel
import com.dam2.jose.compras.navigations.AppScreens
import com.dam2.jose.compras.states.UiState

@Composable
fun FirstScreen(navController: NavController, mvvm: ViewModel){
    val uiState by mvvm.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Tienda compra/venta")
                }
            )
        }
    ) {
        BodyFirstScreen(navController, mvvm, uiState)
    }
}

@Composable
fun BodyFirstScreen(navController: NavController, mvvm: ViewModel, uiState: UiState) {
    val context = LocalContext.current

    Column() {
        Card(
            modifier = Modifier
                .padding(15.dp),
            elevation = 5.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row() {
                    Image(
                        painter = painterResource(id = com.dam2.jose.compras.R.drawable.logo_lambda),
                        contentDescription = "logo tienda",
                        modifier = Modifier
                            .size(180.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Fit
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(50.dp)
                ) {
                    OutlinedButton(onClick = {
                        navController.navigate(route = AppScreens.Comprar.route)
                    }) {
                        Text("Comprar")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    OutlinedButton(onClick = {
                        if (uiState.productosEnCesta.isEmpty()) {
                            Toast.makeText(context, "La cesta está vacía", Toast.LENGTH_SHORT).show()
                        } else {
                            navController.navigate(route = AppScreens.VerCesta.route)
                        }
                    }) {
                        Text("Ver cesta")
                    }
                }
            }
        }
    }
}
