package com.demomiru.weatherdata

fun formatTempForDisplay(temp: Float, tempDisplaySetting: TempDisplaySetting):String{
    return when(tempDisplaySetting){
        TempDisplaySetting.Fahrenheit -> String.format("%.2f F",temp)
        TempDisplaySetting.Celsius -> {
            val Temp = (temp - 32f) * (5f/9f)
            String.format("%.2f C",Temp)
        }
    }

}