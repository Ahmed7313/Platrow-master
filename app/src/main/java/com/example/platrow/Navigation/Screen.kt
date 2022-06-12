package com.example.extendtaskfoodapp.navigation

sealed class Screen(val route : String){
    object RegisterView : Screen(route = "register_screen")
    object LoginView : Screen(route = "login_screen")
    object ProductScreen : Screen(route = "product_screen")
    object MainScreen : Screen(route = "main_screen")
    object CartScreen : Screen(route = "cart_screen")


}