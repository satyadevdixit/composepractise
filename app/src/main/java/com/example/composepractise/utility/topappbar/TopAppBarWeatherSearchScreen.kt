package com.example.composepractise.utility.topappbar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composepractise.navigationdrawer.createAppBar
import com.example.composepractise.utility.showAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun showingWeatherSearchScreen(navController: NavController,drawerState: DrawerState)
{
    var dropDownVisible = remember { mutableStateOf(false) }
    var searchValue = remember { mutableStateOf("") }
    var dismissAlertDialogState = remember { mutableStateOf(false) }

showAlertDialog(dismissAlertDialogState,dropDownVisible)
    Scaffold(topBar = { createAppBar("Search", drawerState) }) {
        Column(modifier = Modifier.padding(top=150.dp)) {
        OutlinedTextField(label = { Text("search")},value = searchValue.value, onValueChange = {searchValue.value = it},
            modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done), singleLine = true,
            keyboardActions = KeyboardActions(onDone = {
    //            navController.navigateUp()
      dismissAlertDialogState.value = true
            })
        )
        }
    }

    var weatherViewModel = WeatherListScreen().getViewModel()
    weatherViewModel?.searchDetailMutableLiveData?.value = searchValue.value
}

@Composable
fun searchScreenMainView(navController: NavController,drawerState: DrawerState)
{
showingWeatherSearchScreen(navController,drawerState)
}
