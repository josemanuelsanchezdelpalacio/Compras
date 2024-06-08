package com.dam2.jose.compras.states

import com.dam2.jose.compras.data.Compra
import com.dam2.jose.compras.data.Datos

data class UiState(
    var productosEnCesta: List<Compra> = mutableListOf(),
    var productoSeleccionado: Datos? = null,
    var productos: List<Datos> = listOf()
)