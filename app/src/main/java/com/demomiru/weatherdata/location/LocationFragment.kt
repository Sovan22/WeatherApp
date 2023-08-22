package com.demomiru.weatherdata.location

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.demomiru.weatherdata.AppNavigator
import com.demomiru.weatherdata.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val key_zip = "zipcode"

/**
 * A simple [Fragment] subclass.
 * Use the [LocationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LocationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var zip: String? = null
    private lateinit var appNavigator: AppNavigator
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNavigator = context as AppNavigator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            zip = it.getString(key_zip)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_location, container, false)
        val enterButton = view.findViewById<Button>(R.id.enterButton)
        val etZip = view.findViewById<EditText>(R.id.editTextNumberDecimal)
        enterButton.setOnClickListener {
            if(etZip.text.length<6) {
                Toast.makeText(requireContext(),"Enter valid zip code", Toast.LENGTH_SHORT).show()
            }
            else{
                appNavigator.navigateToCurrentForecast(etZip.toString())
            }
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LocationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(zip: String) =
            LocationFragment().apply {
                arguments = Bundle().apply {
                    putString(key_zip, zip)
                }
            }
    }
}