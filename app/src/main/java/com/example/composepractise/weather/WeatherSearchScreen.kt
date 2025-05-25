package com.example.composepractise.weather

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavController
import com.example.composepractise.utility.showAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun showingWeatherSearchScreen(navController: NavController)
{
    var dropDownVisible = remember { mutableStateOf(false) }
    var searchValue = remember { mutableStateOf("") }
    var dismissAlertDialogState = remember { mutableStateOf(false) }

showAlertDialog(dismissAlertDialogState,dropDownVisible)
    Scaffold(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TopAppBar(title = { Text("Search") },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back",
                modifier = Modifier.clickable {
                    navController.navigateUp()
                    Log.d(
                        "clickevent",
                        "backbutton click"
                    )
                }
            )
        }
            )
        OutlinedTextField(label = { Text("search")},value = searchValue.value, onValueChange = {searchValue.value = it},
            modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done), singleLine = true,
            keyboardActions = KeyboardActions(onDone = {
    //            navController.navigateUp()
      dismissAlertDialogState.value = true
            })
        )
        } }

    var weatherViewModel = WeatherListScreen().getViewModel()
    weatherViewModel?.searchDetailMutableLiveData?.value = searchValue.value
}

@Composable
fun searchScreenMainView(navController: NavController)
{
showingWeatherSearchScreen(navController)
}
