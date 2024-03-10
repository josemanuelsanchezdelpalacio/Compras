package com.dam2.jose.compras.models

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.dam2.jose.compras.data.Compra
import com.dam2.jose.compras.data.Datos
import com.dam2.jose.compras.states.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun seleccionarProducto(producto: Datos) {
        _uiState.value = _uiState.value.copy(productoSeleccionado = producto)
    }

    fun agregarProductoACesta(producto: Datos) {
        val compra = Compra(nombre = producto.nombre, precio = producto.precio)
        _uiState.value.productosEnCesta.add(compra)
    }

    fun pagar() {
        _uiState.value = _uiState.value.copy(productosEnCesta = mutableListOf())
    }
}
