package com.example.composepractise.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController


fun routeScreen(navController: NavController, route:String){
    navController.navigate(route)
}