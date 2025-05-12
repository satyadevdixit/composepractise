package com.example.composepractise

import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composepractise.navigation.ScreenName

@Composable
fun showData(navController: NavController)
{
    Surface(Modifier.height(200.dp)) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "LIST Screen navigation",Modifier.clickable { navController.navigate(
                ScreenName.SHOWINGLIST.name) }) }
    }
}