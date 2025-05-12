package com.example.composepractise.weather

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun weatherMainView()
{
topAppBar()
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun topAppBar() {
    Scaffold(modifier = Modifier) {
        Card(elevation = CardDefaults.cardElevation(3.dp)) {
            TopAppBar(
                title = {
                    Text(text = "Weather Screen Bar")
                }, navigationIcon = {

                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back", modifier = Modifier.clickable { Log.d("clickevent","backbutton click") }
                    )
                },
                actions = {
                    IconButton(onClick = {Log.d("clickevent","search click")}) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search"
                        )

                    }

                    IconButton(onClick = {Log.d("clickevent","dots click")}) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "dots")
                    }

                })
        }
    }
}
