package com.demomiru.weatherdata


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class WeatherViewHolder(
    view : View,
    private val tempDisplaySettingManager: TempDisplaySettingManager
    ): RecyclerView.ViewHolder(view){
    private val tempText : TextView = view.findViewById(R.id.temp)
    private val desc : TextView = view.findViewById(R.id.desc)

    fun bind(weather: Weather)
    {
        tempText.text = formatTempForDisplay(weather.temp, tempDisplaySettingManager.getTempDisplaySetting())
        desc.text = weather.description
    }
}

class WeatherAdapter(private val tempDisplaySettingManager: TempDisplaySettingManager,
    private val clickHandler : (Weather) -> Unit
) : ListAdapter<Weather, WeatherViewHolder>(DIFF_CONFIG) {

    companion object{
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<Weather>(){
            override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_row_item,parent,false)
        return WeatherViewHolder(itemView, tempDisplaySettingManager)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(position))
        }
    }
}