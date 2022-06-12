package com.example.extendtaskfoodapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.loginappcomposesmaat.views.LoginScreen
import com.example.loginappcomposesmaat.views.RegisterScreen
import com.example.platrow.MainActivity
import com.example.platrow.MyCartScreen
import com.example.platrow.ProductFullScreen


//@Composable
//fun SetupNavGraph(navController: NavHostController) {
//
//    NavHost(navController = navController ,
//        startDestination = Screen.ProductScreen.route ){
//
//
//        composable(route = Screen.MainScreen.route){
//            MainActivity()
//        }
//
//        composable(route = Screen.RegisterView.route){
//            RegisterScreen(modifier = Modifier
//                .wrapContentHeight()
//                .fillMaxSize())
//        }
//
//        composable(route = Screen.LoginView.route){
//            LoginScreen(modifier = Modifier.fillMaxSize())
//        }
//
//        composable(route = Screen.ProductScreen.route){
//            ProductFullScreen()
//        }
//        composable(route = Screen.CartScreen.route){
//            MyCartScreen()
//        }
//    }
//}