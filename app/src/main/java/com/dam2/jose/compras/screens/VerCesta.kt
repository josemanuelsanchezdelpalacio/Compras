package com.dam2.jose.compras.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam2.jose.compras.data.*

@Composable
fun VerCesta(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar() {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver atras",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(8.dp))
                Text("Volver atras")
            }
        }
    ){
        BodyVerCestaScreen(navController)
    }
}

@Composable
fun BodyVerCestaScreen(navController: NavController) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        //Se crea un lazycolumn que almacena un elemento items
        //Ese elemento items almacena la lista con la informacion de los productos que han sido comprados y
        // llama a un metodo para maquetar el LazyColumn
        items(listaComprado) { compra ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.Center),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = compra.nombre,
                            style = MaterialTheme.typography.h6,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "${compra.precio} €",
                            style = MaterialTheme.typography.body1,
                            color = Color.Gray
                        )
                        //si se pulsa Pagar se elimina de la lista listaComprado y muestra un toast con Compra realizada
                        OutlinedButton(
                            onClick = {
                                val compra = Compra(nombre = compra.nombre, precio = compra.precio)
                                listaComprado.remove(compra)
                                Toast.makeText(context, "Compra realizada", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Text(text = "Pagar")
                        }
                    }
                }
            }
        }
    }
}
