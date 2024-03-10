package com.dam2.jose.compras.navigations

sealed class AppScreens(val route: String){
    object FirstScreen: AppScreens("first_screen")
    object Comprar: AppScreens("compras")
    object VerCesta: AppScreens("ver_cestas")
}
