package com.example.composepractise.listexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composepractise.R
import com.example.composepractise.navigationbottombar.bottomNavigationDrawer
import com.example.composepractise.navigationdrawer.navigationDrawer

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navigationDrawer()
        //bottomNavigationDrawer()
        }
    }
}


@Composable
fun mainContent(countdata: Int,updateValue:(Int)->Unit)
{
    var textValue by remember { mutableStateOf("data") }
    var count = countdata
    val context = LocalContext.current
    var checked by remember { mutableStateOf(true) }

    Surface(modifier = Modifier.padding(0.dp,20.dp,0.dp,10.dp),
    color = Color.Red) {
    Row (modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.title_activity_detail)
            )

            Column {
                Text(text = "Boost")
                Row(modifier = Modifier.padding(0.dp,5.dp,0.dp,0.dp)) {
                    Text(text = countdata.toString(), modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp))
                    Text(text = "Running")

                }

            }
        }


Row(modifier = Modifier,
    verticalAlignment = Alignment.CenterVertically) {

    Button(onClick = {
count  = count.plus(1)
        updateValue(count)
        Toast.makeText(context, textValue + checked, Toast.LENGTH_LONG).show()
    },
        modifier = Modifier.padding(0.dp,0.dp,5.dp,0.dp),
        colors = ButtonColors(
            Color.Green,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.DarkGray
        ), elevation = ButtonDefaults.buttonElevation(10.dp),
        shape = RectangleShape,

    ) {
        Text(text = "+")
    }

    Button(onClick = {
        count  = count.minus(1)
        updateValue(count)
        Toast.makeText(context, "click = " + count, Toast.LENGTH_LONG).show()
    },
        modifier = Modifier.padding(0.dp,0.dp,5.dp,0.dp),
        colors = ButtonColors(
            Color.Green,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.DarkGray
        ), elevation = ButtonDefaults.buttonElevation(10.dp),
        shape = RectangleShape,

        ) {
        Text(text = "-")
    }

    Switch(
        modifier = Modifier,
        checked = checked,
        onCheckedChange = {
            checked = it
            Toast.makeText(context, textValue + checked, Toast.LENGTH_LONG).show()
          if (checked)
              updateValue(1)
            else
                updateValue(0)
        },
    )
}
    }
}

}
