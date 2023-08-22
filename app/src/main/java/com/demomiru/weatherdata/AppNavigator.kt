package com.demomiru.weatherdata

interface AppNavigator {
    fun navigateToCurrentForecast(zipcode : String)
    fun navigateToLocationEntry()
}