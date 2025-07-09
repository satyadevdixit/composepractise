package com.example.composepractise.recomposition

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
data class ContactDetails(val name:String, var age:Int)
