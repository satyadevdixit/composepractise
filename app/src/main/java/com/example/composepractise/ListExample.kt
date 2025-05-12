package com.example.composepractise

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.logging.Logger


var name =    mutableStateOf("")
var address =   mutableStateOf("")

@Composable
fun showingCompleteList() {

val listViewModel = viewModel(ListViewModel::class.java)
    Column(Modifier.padding(10.dp)) {
        enterDetails(name.value, address.value,{
            Log.d("showingname_2",it)
            name.value = it })
        {
            address.value = it
        }
        Button(onClick = {listViewModel.addItemInList(name.value+"="+ address.value)
          //  Log.d("showingname_size",languages.size.toString())
        }) { Text("click me") }
        LazyColumn { items(listViewModel.getLanguageList()) { it -> Surface(onClick = {listViewModel.removeItemInList(it)},Modifier.padding(10.dp)) { Text(it) } } }

    }

}


@Composable
fun enterDetails(
    name:String,
    address:String,
    nameMethod: (String) -> Unit,
    addressMethod:(String)->Unit)
{
    Column(modifier = Modifier.padding(10.dp)) {
        TextField(value = name, onValueChange = {
            Log.d("showingname",name)
            nameMethod(it)})
    //    HorizontalDivider(modifier = Modifier,10.dp)
        TextField(value = address, onValueChange = {addressMethod(it)},Modifier.padding(0.dp,10.dp,0.dp,0.dp))
    }

}


