package com.example.composepractise.utility.topappbar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composepractise.R

class TopAppBarWeatherViewModel :ViewModel() {
    var searchDetailMutableLiveData = MutableLiveData<String>()
    val searchDetailLiveData: LiveData<String> get() = searchDetailMutableLiveData
    val weatherList = mutableListOf<TopAppBarWeatherListData>()

    fun getWeatherDayList():MutableList<TopAppBarWeatherListData>
    {
        weatherList.add(TopAppBarWeatherListData("thur", R.drawable.cloud_fog_svgrepo_com,"light rain",45,23))
        weatherList.add(TopAppBarWeatherListData("fri", R.drawable.cloud_fog_svgrepo_com,"rain",50,20))
        weatherList.add(TopAppBarWeatherListData("mon", R.drawable.cloud_fog_svgrepo_com,"heavy rain",55,21))
        weatherList.add(TopAppBarWeatherListData("tue", R.drawable.cloud_fog_svgrepo_com,"thunderbolt",51,25))
        weatherList.add(TopAppBarWeatherListData("wed", R.drawable.cloud_fog_svgrepo_com,"light rain",45,26))
        weatherList.add(TopAppBarWeatherListData("sat", R.drawable.cloud_fog_svgrepo_com,"light rain",41,28))
        weatherList.add(TopAppBarWeatherListData("sun", R.drawable.cloud_fog_svgrepo_com,"light rain",40,29))
        weatherList.add(TopAppBarWeatherListData("thu", R.drawable.cloud_fog_svgrepo_com,"light rain",42,22))
return weatherList
    }
}