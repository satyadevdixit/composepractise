package com.example.composepractise.coroutine

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composepractise.navigationdrawer.createAppBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun coroutineEffect(drawerState: DrawerState,navController: NavController)
{
    var counter = remember { mutableStateOf<Int>(0) }
    val coroutineScope = rememberCoroutineScope()

  //  launchedEffect(counter)
    disposalEffect(counter)
Scaffold(topBar = { createAppBar("Coroutine",drawerState) }) {
    Surface(modifier = Modifier.padding(it)) {
        Column {
            TextButton(onClick = {
                coroutineScope.launch {
                      delay(3000)
                    counter.value = counter.value + 1
                }
            }) {
                Text(text = "click on Me")
            }
            Text(text = "counter = ${counter.value}")
        }
} }
}

@Composable
fun launchedEffect(counter: MutableState<Int>) {
    Log.d("launchedeffect_recompose","launch method called = " +counter.value.toString())
    Surface(modifier = Modifier.padding(300.dp)) {
        Text(text = "lunched effects")
    }
val context = LocalContext.current
//Toast.makeText(context,"launched",Toast.LENGTH_SHORT).show()
    LaunchedEffect(counter.value) {
        delay(5000)
        Log.d("launchedeffect_1", "launch execute = " +counter.value.toString())
    }
}

@Composable
fun disposalEffect(counter: MutableState<Int>)
{
    Log.d("launchedeffect_disposalEffectrecompose","disposable method called = " + counter.value.toString())
DisposableEffect(counter) {
    Log.d("launchedeffect_disposal","disposable effect called = "+ counter.value.toString())
    onDispose {
        Log.d("launchedeffect_disposal_2", "onDispose execute = " +counter.value.toString()) }
}
}
