package com.dam2.jose.compras.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam2.jose.compras.data.Compra
import com.dam2.jose.compras.data.Datos
import com.dam2.jose.compras.data.lista
import com.dam2.jose.compras.data.listaComprado
import com.dam2.jose.compras.models.ViewModel
import com.dam2.jose.compras.navigations.AppScreens


@Composable
fun Comprar(navController: NavController, mvvm: ViewModel) {
    val uiState by mvvm.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar() {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver atrás",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Volver atrás")

                Spacer(modifier = Modifier.width(130.dp))

                Text("Finalizar compra")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Finalizar compra",
                    modifier = Modifier.clickable {
                        navController.navigate(AppScreens.VerCesta.route)
                    }
                )
            }
        }
    ) {
        BodyComprarScreen(navController, mvvm)
    }
}

@Composable
fun BodyComprarScreen(navController: NavController, mvvm: ViewModel) {
    val context = LocalContext.current
    val uiState by mvvm.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(lista.toList()) { productos ->
            var expanded by remember { mutableStateOf(false) }

            Card(
                modifier = Modifier
                    .padding(15.dp)
                    .clickable { expanded = !expanded },
                elevation = 5.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .padding(start = 16.dp),
                        painter = painterResource(id = productos.foto),
                        contentDescription = productos.nombre,
                        alignment = Alignment.Center
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = productos.nombre,
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = if (expanded) productos.car_larga else productos.caracteristicas,
                            style = MaterialTheme.typography.body2,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "${productos.precio} €",
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center
                        )
                        if (expanded) {
                            OutlinedButton(
                                onClick = {
                                    mvvm.agregarProductoACesta(productos)
                                    Toast.makeText(context, "El producto ${productos.nombre} se ha añadido a la lista de la compra", Toast.LENGTH_SHORT).show()
                                }
                            ) {
                                Text(text = "Comprar")
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            OutlinedButton(onClick = {
                                navController.navigate(route = AppScreens.FirstScreen.route)
                                navController.navigate(route = AppScreens.Comprar.route)
                            }) {
                                Text("Volver")
                            }
                        }
                    }
                }
            }
        }
    }
}


