package com.demomiru.weatherdata.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demomiru.weatherdata.AppNavigator
import com.demomiru.weatherdata.ForecastRepository
import com.demomiru.weatherdata.R
import com.demomiru.weatherdata.TempDisplaySettingManager
import com.demomiru.weatherdata.Weather
import com.demomiru.weatherdata.WeatherAdapter
import com.demomiru.weatherdata.details.ForecastDetailsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val key_zip = "zipcode"

/**
 * A simple [Fragment] subclass.
 * Use the [CurrentForecastFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CurrentForecastFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var zip: String? = null

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    private val forecastRepository = ForecastRepository()
    private lateinit var appNavigator: AppNavigator
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNavigator = context as AppNavigator
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())
        arguments?.let {
            zip = it.getString(key_zip)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_current_forecast, container, false)

        val rcView = view.findViewById<RecyclerView>(R.id.rvWeather)
        rcView.layoutManager = LinearLayoutManager(requireContext())

        val lButton : FloatingActionButton = view.findViewById(R.id.locationReturnButton)
        lButton.setOnClickListener {
            appNavigator.navigateToLocationEntry()
        }

        val forecastAdapter = WeatherAdapter(tempDisplaySettingManager){
            Toast.makeText(requireContext(),"Clicked Item", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), ForecastDetailsActivity::class.java)
            intent.putExtra("key-temp",it.temp)
            intent.putExtra("key-desc",it.description)
            startActivity(intent)

        }
        rcView.adapter = forecastAdapter

        val weeklyForecastObserver = Observer<List<Weather>>{ forecastItems->
            //update our list adapter
            forecastAdapter.submitList(forecastItems)
        }

        forecastRepository.weeklyForecast.observe(this,weeklyForecastObserver)
        forecastRepository.loadForecast(zip!!)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(zip: String) =
            CurrentForecastFragment().apply {
                arguments = Bundle().apply {
                    putString(key_zip, zip)
                }
            }
    }
}