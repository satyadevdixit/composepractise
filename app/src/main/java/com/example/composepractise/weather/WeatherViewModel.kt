package com.example.composepractise.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composepractise.R

class WeatherViewModel :ViewModel() {
    var searchDetailMutableLiveData = MutableLiveData<String>()
  //  var dropDownVisibleLiveData = MutableLiveData<Boolean>()
    val searchDetailLiveData: LiveData<String> get() = searchDetailMutableLiveData
    val weatherList = mutableListOf<WeatherListData>()

    fun getWeatherDayList():MutableList<WeatherListData>
    {
        weatherList.add(WeatherListData("thur", R.drawable.cloud_fog_svgrepo_com,"light rain",45,23))
        weatherList.add(WeatherListData("fri", R.drawable.cloud_fog_svgrepo_com,"rain",50,20))
        weatherList.add(WeatherListData("mon", R.drawable.cloud_fog_svgrepo_com,"heavy rain",55,21))
        weatherList.add(WeatherListData("tue", R.drawable.cloud_fog_svgrepo_com,"thunderbolt",51,25))
        weatherList.add(WeatherListData("wed", R.drawable.cloud_fog_svgrepo_com,"light rain",45,26))
        weatherList.add(WeatherListData("sat", R.drawable.cloud_fog_svgrepo_com,"light rain",41,28))
        weatherList.add(WeatherListData("sun", R.drawable.cloud_fog_svgrepo_com,"light rain",40,29))
        weatherList.add(WeatherListData("thu", R.drawable.cloud_fog_svgrepo_com,"light rain",42,22))
return weatherList
    }

 /*   fun getDropDownVisibility():LiveData<Boolean>
    {
        return dropDownVisibleLiveData as LiveData<Boolean>
    }*/
}