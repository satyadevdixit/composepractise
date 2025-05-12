package com.example.composepractise

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

class ListViewModel:ViewModel() {
    val languages = mutableStateListOf<String>()

    fun getLanguageList(): SnapshotStateList<String> {
 return languages
    }

    fun addItemInList(item:String)
    {
        languages.add(item)
    }

    fun removeItemInList(item:String)
    {
        languages.remove(item)
    }
}