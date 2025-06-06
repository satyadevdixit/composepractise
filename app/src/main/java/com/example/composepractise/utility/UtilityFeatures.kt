package com.example.composepractise.utility

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun showAlertDialog(dismissAlertState: MutableState<Boolean>, dropDownExpand: MutableState<Boolean>) {

    if (dismissAlertState.value)
        AlertDialog(
            icon = {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Example Icon")
            },
            title = {
                Text(text = "title Dialog")
            },
            text = {
                Text(text = "Select Yes or No")
            },
            onDismissRequest = {
                dismissAlertState.value = false
            },
            confirmButton = {
                TextButton(onClick = {dismissAlertState.value= false
                    dropDownExpand.value = false}) { Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dropDownExpand.value = false
                        dismissAlertState.value= false
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showBottomSheet(bottomSheetState: MutableState<Boolean>)
{
    if (bottomSheetState.value) {
        ModalBottomSheet(onDismissRequest = {}) {
            Column { Text(text = "Bottom Sheet")
                Button(onClick = {bottomSheetState.value = false}, modifier = Modifier.padding(10.dp))
                { Text(text = "click on me") }
            }
        }
    }
}

@Composable
fun showFabButton(click:()->Unit) {
        FloatingActionButton(
            onClick = { click() }
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Fab click")
        }
}
