package com.example.composepractise.recomposition

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("RememberReturnType")
@Composable
fun recompositionExample()
{
//recompositionWithStableParameters()
    var countData = remember { Log.d("remembervalue","execute countData")
        mutableStateOf(0)
    }
    var count = remember {
        countData.value+1}
    checkRememberFunctionality(count,countData.value,click = {
countData.value = it
    })
}


@Composable
fun recompositionWithStableParameters()
{
    Log.d("Stability", "recompositionWithStableParameters method called")
    var checked = remember { mutableStateOf(true) }
    var contactDetails = remember {  mutableStateOf(ContactDetails("dixit sahab",31))  }
  //  var contactDetails = remember {  ContactDetails("dixit sahab",31)  }
   // contactDetails.age = 89

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Log.d("Stability", "recompositionMethod Age Value = ${contactDetails.value.age}")
        showContactDetails(contactDetails.value.age,"Hello")
        showToggleButton(
            checked.value,
            onStateChanged = {
                contactDetails.value.age = contactDetails.value.age +1
                checked.value = it
                Log.d("Stability", "Toggle Button state changed = ${contactDetails.value.age}")
            }
        )
    }
}

@Composable
fun recompositionWithUnStableParameters()
{

}

@Composable
fun recomposableWithRemember()
{

}

@Composable
fun showToggleButton(selected: Boolean, onStateChanged: (Boolean) -> Unit) {
    Switch(checked = selected, onCheckedChange = {
        onStateChanged(it)
    })

    Log.d("Stability", "showToggleButton Method called")
}


@Composable
fun showContactDetails(contact: Int,dataVal:String) {
    Log.d("Stability", "showContactMethod Age = ${contact}")
    Text(text = "Name: ${contact}, Age: ${contact}")
}


@SuppressLint("RememberReturnType")
@Composable
fun checkRememberFunctionality(count: Int,countData: Int,click:(Int)->Unit)
{
    Log.d("remembervalue","checkRememberFunctionality")
    checkMethodComposition(count,countData)
 Surface(modifier = Modifier.padding(50.dp)) {
     Log.d("remembervalue","surface in")
     Row(modifier = Modifier.fillMaxWidth()) {
         Log.d("remembervalue","row in")
         Text(text = "count 1: = ${count}")
         Spacer(modifier = Modifier.width(10.dp))
         Text(text = "countData 2: = ${countData}")
         Button(onClick = {click(countData+1)
            /* Log.d("remembervalue","click me = ${countData.value}")*/}) { Text(text = "click me") }
     }
 }

}

@Composable
fun checkMethodComposition(count:Int,countData:Int)
{
    Log.d("remembervalue","checkMethodComposition method called")
    Surface(modifier = Modifier.padding(top = 250.dp, start = 100.dp)) {
        Log.d("remembervalue","checkMethodComposition surface in")
        Row(modifier = Modifier.fillMaxWidth()) {
            Log.d("remembervalue","checkMethodComposition row in")
            Text(text = "count: = ${count}")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "countData: = ${countData}")
        }
    }
}