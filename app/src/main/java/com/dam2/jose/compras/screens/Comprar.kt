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
import com.dam2.jose.compras.navigations.AppScreens

private var infoProductos by mutableStateOf(true)

@Composable
fun Comprar(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar() {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver atras",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Volver atras")

                Spacer(modifier = Modifier.width(130.dp))

                Text("Finalizar compra")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Finalizar compra",
                    modifier = Modifier.clickable {
                        navController.navigate(route = AppScreens.VerCesta.route)
                    }
                )
            }
        }
    ){
        BodyComprarScreen(navController)
    }
}

@Composable
fun BodyComprarScreen(navController: NavController) {
    val context = LocalContext.current
    //Se crea un lazycolumn que almacena un elemento items
    //Ese elemento items almacena la lista con la informacion de los productos y llama a un metodo para maquetar el LazyColumn
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(lista.toList()) { productos ->
            //se crea el Card con la informacion
            Card(
                modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                        infoProductos = !infoProductos
                    },
                elevation = 5.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //si infoProductos es true muestra la información con las caracteristicas cortas y si se hace
                    // clic en la Card se muestra las caracteristicas largas y los botones de Comprar y volver
                    if(infoProductos) {
                        Row(
                            modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.Center),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(200.dp)
                                    .padding(start = 16.dp),
                                painter = painterResource(id = productos.foto),
                                contentDescription = productos.nombre,
                                alignment = Alignment.Center
                            )
                            Column (
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ){
                                Text(
                                    text = productos.nombre,
                                    style = MaterialTheme.typography.h6,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = productos.caracteristicas,
                                    style = MaterialTheme.typography.body2,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Box(Modifier.fillMaxSize()) {
                                Text(
                                    text = "${productos.precio} €",
                                    style = MaterialTheme.typography.h6,
                                    modifier = Modifier.align(Alignment.TopCenter)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Box(
                                    Modifier.align(Alignment.TopCenter).fillMaxHeight().width(50.dp)
                                )
                            }
                        }
                    }else {
                        Image(
                            modifier = Modifier
                                .size(200.dp)
                                .padding(start = 16.dp),
                            painter = painterResource(id = productos.foto),
                            contentDescription = productos.nombre,
                            alignment = Alignment.Center
                        )
                        Box(Modifier.fillMaxSize()) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = productos.nombre,
                                    style = MaterialTheme.typography.h6,
                                )
                                Text(
                                    text = productos.car_larga,
                                    style = MaterialTheme.typography.body2,
                                )
                                Text(
                                    text = "${productos.precio} €",
                                    style = MaterialTheme.typography.h6,
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .padding(50.dp)
                        ) {
                            //si se pulsa Comprar el nombre y el precio se guarda en la lista listaComprado
                            OutlinedButton(
                                onClick = {
                                    val compra = Compra(nombre = productos.nombre, precio = productos.precio)
                                    listaComprado.add(compra)
                                    Toast.makeText(context, "El producto" + productos.nombre + "se ha añadido a la lista de la compra", Toast.LENGTH_SHORT).show()
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
