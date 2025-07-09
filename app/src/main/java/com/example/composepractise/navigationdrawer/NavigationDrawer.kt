package com.example.composepractise.navigationdrawer

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composepractise.coroutine.coroutineEffect
import com.example.composepractise.listexample.mainContent
import com.example.composepractise.navigation.ScreenName
import com.example.composepractise.readingbook.showReadingBookScreen
import com.example.composepractise.listexample.showingCompleteList
import com.example.composepractise.questionexample.mainViewQuestionScreen
import com.example.composepractise.recomposition.recompositionExample
import com.example.composepractise.utility.topappbar.weatherMainViewTopAppBar
import com.example.composepractise.weather.searchScreenMainView
import com.example.composepractise.weather.showingWeatherSearchScreen
import com.example.composepractise.weather.weatherMainView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun navigationDrawer()
{
    navigationDrawerModel("Title")
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun navigationDrawerModel(title:String) {
    val navController = rememberNavController()
    val context = LocalContext.current
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

ModalNavigationDrawer(drawerState = drawerState,drawerContent = {
    ModalDrawerSheet {
        createMenuItems(drawerState,navController)

    }
})
{
    NavHost(navController, startDestination = ScreenName.READINGBOOK.name) {

        composable(route = ScreenName.SEARCHSCREEN.name)
        {
            searchScreenMainView(navController = navController,drawerState)
        }

        composable(route = ScreenName.READINGBOOK.name)
        {

            showReadingBookScreen(navController,drawerState)
        }


        composable(route = ScreenName.WEATHERSCREENTOPAPPBAR.name) {
            weatherMainViewTopAppBar(navController,drawerState)
        }

        composable(route = ScreenName.WEATHERSCREEN.name) {
            weatherMainView(navController,drawerState)
        }
        composable(route = ScreenName.DETAIL.name) {
            mainContent(10,{ })
        }

        composable(route = ScreenName.SHOWINGLIST.name) {
            showingCompleteList()
        }

        composable(route = ScreenName.QUESTIONSCREEN.name) {
            mainViewQuestionScreen(drawerState,navController)
        }

        composable(route = ScreenName.COROUTINE.name) {
            coroutineEffect(drawerState,navController)
        }

        composable(route = ScreenName.RECOMPOSITION.name)
        {
            recompositionExample()
        }

    }
}
}

fun createMenuItemsList():List<NavigationDrawerItem>
{
val list = mutableListOf<NavigationDrawerItem>()
    list.add(NavigationDrawerItem(Icons.Default.Search,"Search",100))
    list.add(NavigationDrawerItem(Icons.Default.Favorite,"Reading",90))
    list.add(NavigationDrawerItem(Icons.Default.Face,"Weather",80))
    list.add(NavigationDrawerItem(Icons.Default.Build,"Question",70))
    list.add(NavigationDrawerItem(Icons.Default.Create,"Coroutine",50))
    list.add(NavigationDrawerItem(Icons.Default.DateRange,"Top App Bar",50))

    return list
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createAppBar(title: String,drawerState: DrawerState)
{
    val coroutineScope = rememberCoroutineScope()

  /*  CenterAlignedTopAppBar(
        navigationIcon = {
            if (drawerState != null) {
                IconButton(onClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }) {
                    Icon(Icons.Filled.Menu, contentDescription = "")
                }
            }
        },
        title = { Text(text = title) }
    )*/

    TopAppBar(
        title = {
            Text(text = title)
        }, navigationIcon = {

            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "back",
                modifier = Modifier.clickable {
                    //navController.navigate(ScreenName.SEARCHSCREEN.name)
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }
            )
        },
        actions = {
            IconButton(onClick = {
            //    navController.navigate(ScreenName.SEARCHSCREEN.name)
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search"
                )

            }

            IconButton(onClick = {
               // dropDownVisible.value = true
            }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "dots")
            }

        })

}

@Composable
fun createMenuItems(drawerState: DrawerState,navController: NavController)
{
    val coroutineScope = rememberCoroutineScope()
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    Column {
        createMenuItemsList().forEachIndexed { index, it ->
val color = if (selectedNavigationIndex.intValue==index) Color.Gray else Color.White
            Row(modifier = Modifier.background(color).padding(10.dp).fillMaxWidth().clickable {
                coroutineScope.launch { drawerState.close() }
                screenNavigation(index,navController)
                selectedNavigationIndex.intValue = index
            }
            ) {
                Icon(imageVector = it.icon, contentDescription = "")
                Text(
                    text = it.title,
                    modifier = Modifier.padding(start = 10.dp)
                        , fontSize = (20.sp)
                )

                Text(
                    text = it.count.toString(),
                    modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
                    textAlign = TextAlign.End,
                    fontSize = (20.sp)
                )

            }
        }
    }
}

fun screenNavigation(index:Int, navController: NavController)
{
    when(index){
        0-> navController.navigate(ScreenName.SEARCHSCREEN.name)
        1-> navController.navigate(ScreenName.READINGBOOK.name)
        2-> navController.navigate(ScreenName.WEATHERSCREEN.name)
        3-> navController.navigate(ScreenName.QUESTIONSCREEN.name)
        4-> navController.navigate(ScreenName.COROUTINE.name)
        5-> navController.navigate(ScreenName.WEATHERSCREENTOPAPPBAR.name)
    }
}
