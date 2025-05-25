package com.example.composepractise.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composepractise.mainContent
import com.example.composepractise.showingCompleteList
import com.example.composepractise.weather.searchScreenMainView
import com.example.composepractise.weather.weatherMainView

enum class ScreenName{
    LIST,
    DETAIL,
    SHOWINGLIST,
    QUESTIONSCREEN,
    WEATHERSCREEN,
    SEARCHSCREEN
}

@Composable
fun NavigationScreen()
{
      var navController = rememberNavController()

    NavHost(navController, startDestination = ScreenName.WEATHERSCREEN.name) {

        composable(route = ScreenName.WEATHERSCREEN.name) {
            weatherMainView(navController)
    }
        composable(route = ScreenName.DETAIL.name) {
            mainContent(10,{Log.d("value is","$it")})
        }

        composable(route = ScreenName.SHOWINGLIST.name) {
            showingCompleteList()
        }

        composable(route = ScreenName.SEARCHSCREEN.name)
        {
            searchScreenMainView(navController = navController)
        }
}
    }