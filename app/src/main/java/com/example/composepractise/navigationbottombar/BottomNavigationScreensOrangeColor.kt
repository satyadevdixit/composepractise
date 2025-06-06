package com.example.composepractise.navigationbottombar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun orangeColorScreen()
{
    Surface(modifier = Modifier.padding(100.dp)) {
        Text(text = "Orange Color Screen", textAlign = TextAlign.Center)
    }
}