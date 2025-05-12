package com.example.composepractise

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProduceStateScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay



@Composable
fun rowExample(name: String, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "Hi $name!",
        )
        Text(
            text = "Good bye $name!",
            //modifier = modifier
            modifier
                .clip(CircleShape)
                .background(Color.Red)
                .clickable {
                    Log.e("click done", "button")
                }
        )
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //  columnExample("compose start")
              showingListView()
         //   composeLaunchedSideEffects()
//disposalEffect()
          //  produceStateExample()
            forPreview()
        }
    }


    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        //   ComposePractiseTheme {
        Greeting("Android Compose")
        // }
    }

    @Composable
    fun columnExample(name: String, modifier: Modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hi $name!",
            )
            Text(
                text = "Good bye $name!",
                //modifier = modifier
                modifier
                    .clip(CircleShape)
                    .background(Color.Red).padding(20.dp)
                    .clickable {
                        Toast.makeText(context, "click on me", Toast.LENGTH_SHORT).show()
                        Log.e("click done", "button")
                    }
            )
        }
    }


    @Composable
    fun boxExample(name: String, modifier: Modifier = Modifier) {
        Box(modifier.background(Color.Cyan)) { // Text(
            // text = "Good bye $name!",
            //modifier = modifier
            /* modifier
             .clip(CircleShape)
             .background(Color.Red)
             .clickable {
              }*/
            //)
            val context = LocalContext.current
            modifier.clickable {
                Toast.makeText(context, "click on me", Toast.LENGTH_SHORT)
                Log.e("click on done", "right on me")
            }

            Image(
                painter = painterResource(R.drawable.ic_launcher_background), "",
                modifier
                    .clip(CircleShape)
                    .background(Color.Red)
            )
            Text(text = "Good bye $name!",
                modifier.clickable { }
            )
        }
    }


    @Composable
    fun getInputData() {
        var editTextValue = remember { mutableStateOf("new data") }
        TextField(value = editTextValue.value, onValueChange = {
            editTextValue.value = it
        })
    }


    @Composable
    fun showingListView() {
        val languages = listOf(
            "C++", "C", "C#", "Java", "Kotlin", "Dart", "Python", "Javascript", "SpringBoot",
            "XML", "Dart", "Node JS", "Typescript", "Dot Net", "GoLang", "MongoDb",
        )
        LazyColumn { items(languages) { it -> columnExample(it) } }

    }


    @Preview
    @Composable
    fun forPreview() {
        // boxExample("composable")
        //  getInputData()
        showingListView()
    }

    @Composable
    fun composeLaunchedSideEffects() {
        val dataStateValue = remember { mutableStateOf(0) }
        Log.d("datastatevalue", dataStateValue.value.toString())
        var counter = rememberUpdatedState(10)
        LaunchedEffect(Unit) {
            delay(10000)
            Log.d("datastatevalue counter", counter.value.toString())
        }
        Column(
            modifier = Modifier.padding(20.dp).fillMaxHeight(),
            verticalArrangement = Arrangement.Center,

            ) {
            Text(
                text = dataStateValue.value.toString(),
                fontSize = 50.sp
            )

            Button(onClick = {
                dataStateValue.value++
                counter.value + 1
            }) {
                Text(
                    "click on me",
                    fontSize = 40.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }


    @Composable
    fun disposalEffect() {
        var counter = remember { mutableStateOf(10)}
        Column(
            modifier = Modifier.padding(20.dp).fillMaxHeight(),
            verticalArrangement = Arrangement.Center) {
            Log.d("disposal", "${counter.value}")
            DisposableEffect(counter.value) {
                Log.d("disposal_1", "${counter.value}")
                onDispose {
                    Log.d("disposal ondispose", "ondispose")
                }
            }

            Button(onClick = { ++counter.value }) {
                Text(
                    "click on me",
                    fontSize = 40.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }

    @Composable
    fun produceStateExample()
    {
         var counter = produceState(initialValue = 0) {
             for (i in 0..10) {
                 delay(5000)
                 value++
             }
         }
        Log.d("producestate","${counter.value}")

        Button(onClick = { counter.value }) {
            Text(
                "click on me",
                fontSize = 40.sp,
                fontStyle = FontStyle.Italic
            )
        }
         }
    }




