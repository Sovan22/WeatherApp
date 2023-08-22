package com.demomiru.weatherdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository(){
    private val _weeklyForecast = MutableLiveData<List<Weather>>()
    val weeklyForecast : LiveData<List<Weather>> = _weeklyForecast

    fun loadForecast(zipcode : String){
        val randomValue = List(20){ Random.nextFloat().rem(100) * 100}
        val forecastItems = randomValue.map{
            Weather(it,"Partly Cloudy")
        }

        _weeklyForecast.value = forecastItems
    }

}