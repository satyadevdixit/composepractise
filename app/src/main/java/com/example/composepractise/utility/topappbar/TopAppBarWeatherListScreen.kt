package com.example.composepractise.utility.topappbar

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composepractise.R
import com.example.composepractise.navigation.ScreenName
import com.example.composepractise.navigationdrawer.createAppBar
import com.example.composepractise.utility.showAlertDialog
import com.example.composepractise.utility.showBottomSheet

var weatherViewModel: TopAppBarWeatherViewModel? = null


class WeatherListScreen {
    fun getViewModel(): TopAppBarWeatherViewModel? {
        return weatherViewModel
    }
}

@Composable
fun observeSearchDetails()
{
    var searchDetailQuery  =   weatherViewModel!!.searchDetailLiveData.observeAsState()
    if (!searchDetailQuery.value.toString().equals("null")) {
        Text(text = searchDetailQuery.value.toString())
    }
}


@Composable
fun weatherMainViewTopAppBar(navController: NavController, drawerState: DrawerState)
{
    weatherViewModel = viewModel(TopAppBarWeatherViewModel::class.java)
topAppBar(navController, drawerState)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun topAppBar(navController: NavController, drawerState: DrawerState) {
    var dropDownVisible = remember { mutableStateOf(false) }
    var dismissAlertDialogState = remember { mutableStateOf(false) }
    var bottomSheetState = remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.padding(10.dp)) {
        Column {
            Card(elevation = CardDefaults.cardElevation(3.dp)) {
                TopAppBar(
                    title = {
                        Text(text = "Weather Screen Bar")
                    }, navigationIcon = {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                            modifier = Modifier.clickable {
                                navController.navigate(ScreenName.SEARCHSCREEN.name)
                            }
                        )
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate(ScreenName.SEARCHSCREEN.name) }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "search"
                            )

                        }

                        IconButton(onClick = {
                            dropDownVisible.value = true
                        }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "dots")
                        }

                    })
            }
            showingDropDown(bottomSheetState,dropDownVisible,navController,dismissAlertDialogState)
            showAlertDialog(dismissAlertDialogState,dropDownVisible)
            showBottomSheet(bottomSheetState)
            currentWeather()
            showingWindData()
            Divider(
                color = Color.Black.copy(alpha = 0.2f),
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
            )
observeSearchDetails()
            showingSunSetTiming()
            LazyColumn {
                items(weatherViewModel!!.getWeatherDayList()){
                    it-> listItemView(it)
                }
            }

        }
    }

}

@Composable
fun currentWeather() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Monday, 29 June", modifier = Modifier.padding(0.dp,10.dp,0.dp,0.dp))
        Surface(
            shape = CircleShape,
            modifier = Modifier.padding(10.dp).size(200.dp),
            color = Color.Yellow
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(80.dp),
                    painter = painterResource(R.drawable.cloud_fog_svgrepo_com),
                    contentDescription = "weather"
                )
                Text(text = "54C", fontWeight = FontWeight.Bold,modifier = Modifier.padding(0.dp,10.dp,0.dp,0.dp))
                Text(text = "Rain", modifier = Modifier.padding(0.dp,10.dp,0.dp,0.dp), fontStyle = FontStyle.Italic)
            }
        }
    }
}

@Composable

fun showingWindData()
{
    Surface {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Row {  Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search"
            )
                Text(text = "90%",Modifier.padding(5.dp,0.dp,0.dp,0.dp))
            }

            Row {
                Icon(
                imageVector = Icons.Default.Build,
                contentDescription = "search"
            )
                Text(text = "psis",Modifier.padding(5.dp,0.dp,0.dp,0.dp))
            }
            Row {  Icon(
                imageVector = Icons.Default.ThumbUp,
                contentDescription = "search"
            )
                Text(text = "good",Modifier.padding(5.dp,0.dp,0.dp,0.dp))
            }

        }
    }
}

@Composable
fun showingSunSetTiming()
{
    Surface {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Row {
                Icon(imageVector = Icons.Default.Call, contentDescription = "sunset")
                Text(text = "11:00 AM", modifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp))
            }
            Row {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "sunset")
                Text(text = "07:00 PM", modifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp))
            }
        }
    }
}

@Composable
fun listItemView(weatherListData: TopAppBarWeatherListData)
{
    Surface(modifier = Modifier.padding(10.dp), shape = CircleShape, color = Color.LightGray) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text(text = weatherListData.day)
            Image(
                painter = painterResource(weatherListData.weatherType),
                contentDescription = "weather",
                modifier = Modifier.height(20.dp).width(20.dp)
            )

            Surface(shape = CircleShape, color = Color.Yellow, modifier = Modifier.padding(0.dp))
            {
                Text(text = weatherListData.rainDetail, modifier = Modifier.padding(4.dp))
            }

            Row {
                Text(text = "${weatherListData.dayTemperature} C")
                Text(text = "${weatherListData.nightTemperature} C",Modifier.padding(5.dp,0.dp,0.dp,0.dp))
            }
        }
    }
}

@Composable
fun showingDropDown(bottomSheetState: MutableState<Boolean>, dropDownExpand:MutableState<Boolean>, navController: NavController, dismissAlertState: MutableState<Boolean>)
{
        Column(modifier = Modifier.fillMaxWidth().wrapContentSize(align = Alignment.TopEnd).absolutePadding(right = 20.dp)) {
DropdownMenu(expanded = dropDownExpand.value, onDismissRequest = {dropDownExpand.value = false}) {

    DropdownMenuItem(
        text = { Text("Option 1") },
        leadingIcon = { Icon(imageVector = Icons.Default.Favorite, contentDescription = "fav") },
        onClick = {
        dropDownExpand.value = false
        bottomSheetState.value = true}
            )

    DropdownMenuItem(
        text = { Text("Option 2") },
        leadingIcon = { Icon(imageVector = Icons.Default.Face, contentDescription = "fav") },
        onClick = {dropDownExpand.value = false
            navController.navigate(ScreenName.SEARCHSCREEN.name)
        }
    )
}
}
}

