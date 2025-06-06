package com.example.composepractise.navigationdrawer

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationDrawerItem(val icon: ImageVector, val title:String, val count: Int, val route:String = "")
