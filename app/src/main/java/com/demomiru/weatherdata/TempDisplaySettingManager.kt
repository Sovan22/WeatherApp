package com.demomiru.weatherdata

import android.content.Context
enum class TempDisplaySetting{
    Fahrenheit, Celsius
}
class TempDisplaySettingManager(context :Context) {
    private val preferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE)

    fun updateSetting(setting : TempDisplaySetting){
        preferences.edit().putString("key-temp-display",setting.name).apply()
    }

    fun getTempDisplaySetting() : TempDisplaySetting{
        val settingValue = preferences.getString("key-temp-display",TempDisplaySetting.Fahrenheit.name)
        return  TempDisplaySetting.valueOf(settingValue!!)
    }

}