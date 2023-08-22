package com.demomiru.weatherdata.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.demomiru.weatherdata.R
import com.demomiru.weatherdata.TempDisplaySetting
import com.demomiru.weatherdata.TempDisplaySettingManager
import com.demomiru.weatherdata.formatTempForDisplay

class ForecastDetailsActivity : AppCompatActivity() {
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_forecast_details)
        title = "Forecast Details"
        tempDisplaySettingManager = TempDisplaySettingManager(this)
        val tempText = findViewById<TextView>(R.id.tempTemp)
        val desc = findViewById<TextView>(R.id.tempDescription)
        val temp = intent.getFloatExtra("key-temp",0f)
        tempText.text = formatTempForDisplay(temp,tempDisplaySettingManager.getTempDisplaySetting())
        desc.text = "${intent.getStringExtra("key-desc")}"

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.forecast_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.displayUnit -> {
                showAlertDialog()
                true
            }
            else-> super.onOptionsItemSelected(item)
        }
    }

    private fun showAlertDialog(){
        val dialog = AlertDialog.Builder(this)
            .setTitle("Choose Display Units")
            .setMessage("Choose which temperature unit to display")
            .setPositiveButton("C"){_,_ -> tempDisplaySettingManager.updateSetting(TempDisplaySetting.Celsius)}
            .setNeutralButton("F"){_, _ -> tempDisplaySettingManager.updateSetting(TempDisplaySetting.Fahrenheit)}
            .setOnDismissListener{
                Toast.makeText(this,"setting take place on app restart",Toast.LENGTH_SHORT).show()
            }

        dialog.show()

    }
}