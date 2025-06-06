package com.example.composepractise.navigationbottombar

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composepractise.listexample.mainContent
import com.example.composepractise.listexample.showingCompleteList
import com.example.composepractise.navigation.ScreenName
import com.example.composepractise.navigationdrawer.NavigationDrawerItem
import com.example.composepractise.questionexample.mainViewQuestionScreen
import com.example.composepractise.readingbook.showReadingBookScreen
import com.example.composepractise.utility.topappbar.weatherMainViewTopAppBar
import com.example.composepractise.weather.weatherMainView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun bottomNavigationDrawer()
{
    val navController = rememberNavController()
    Scaffold(bottomBar = { bottomNavigationDrawerItems(navController) }) {
        NavHost(navController, startDestination = ScreenName.BLUECOLOR.name) {

            composable(route = ScreenName.BLUECOLOR.name)
            {
                   blueColorSceen()
            }

            composable(route = ScreenName.REDCOLOR.name)
            {

                redColorScreen()
            }


            composable(route = ScreenName.ORANGECOLOR.name) {
               orangeColorScreen()
            }

        }
    }
}

@Composable
fun bottomNavigationDrawerItems(navController: NavController
) {
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
NavigationBar {
    getItemList().forEachIndexed { index, item ->
        NavigationBarItem(
            selected = selectedNavigationIndex.intValue == index,
            onClick = {
                selectedNavigationIndex.intValue = index
                navController.navigate(item.route)
            },
            icon = {
                Icon(imageVector = item.icon, contentDescription = item.title)
            },
            label = {
                Text(
                    item.title,
                    color = if (index == selectedNavigationIndex.intValue)
                        Color.Black
                    else Color.Gray
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.surface,
                indicatorColor = MaterialTheme.colorScheme.primary
            )
        )
}
}
    }

fun getItemList():List<NavigationDrawerItem>
{
    val list = mutableListOf<NavigationDrawerItem>()
    list.add(NavigationDrawerItem(Icons.Default.Search,"Search",100,ScreenName.REDCOLOR.name))
    list.add(NavigationDrawerItem(Icons.Default.Favorite,"Reading",90,ScreenName.BLUECOLOR.name))
    list.add(NavigationDrawerItem(Icons.Default.Face,"Weather",80,ScreenName.ORANGECOLOR.name))

    return list
}
