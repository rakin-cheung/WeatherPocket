package com.weatherpocket.android.ui.weather

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.weatherpocket.android.R
import com.weatherpocket.android.databinding.ActivityWeatherBinding
import com.weatherpocket.android.logic.model.Weather
import com.weatherpocket.android.logic.model.getSky
import java.text.SimpleDateFormat
import java.util.*


class WeatherActivity : AppCompatActivity() {

    private lateinit var binding:ActivityWeatherBinding

    val viewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Log.d("Weather","core R")
            window.setDecorFitsSystemWindows(false)
        } else {
            Log.d("Weather","less than core R")
            val decorView = window.decorView
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
        window.statusBarColor = Color.TRANSPARENT

        setContentView(binding.root)

        if(viewModel.locationLng.isEmpty()){
            viewModel.locationLng = intent.getStringExtra("location_lng")?:""
            Log.d("Weather",intent.getStringExtra("location_lng")?:"none")
        }
        if(viewModel.locationLat.isEmpty()){
            viewModel.locationLat = intent.getStringExtra("location_lat")?:""
            Log.d("Weather",intent.getStringExtra("location_lat")?:"none")
        }
        if(viewModel.placeName.isEmpty()){
            viewModel.placeName = intent.getStringExtra("place_name")?:""
            Log.d("Weather",intent.getStringExtra("place_name")?:"none")
        }
        viewModel.weatherLiveData.observe(this, Observer { result ->
            val weather = result.getOrNull()
            if(weather != null){
                showWeatherInfo(weather)
            }else{
                Toast.makeText(this,"Not search anything...", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
        viewModel.refreshWeather(viewModel.locationLng,viewModel.locationLat)
    }

    private fun showWeatherInfo(weather:Weather){
        Log.d("Weather","showWeatherInfo")
        binding.nowInclude.placeName.text = viewModel.placeName
        val realtime = weather.realtime
        val daily = weather.daily

        val currentTempText = "${realtime.temperature.toInt()}°C"
        binding.nowInclude.currentTemp.text = currentTempText
        binding.nowInclude.currentSky.text = getSky(realtime.skycon).info
        val currentPM25Text = "${realtime.airQuality.aqi.chn.toInt()}"
        binding.nowInclude.currentAQI.text = currentPM25Text
        binding.nowInclude.nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)

        binding.forecastInclude.forecastLayout.removeAllViews()
        val days = daily.skycon.size
        for (i in 0 until days){
            val skycon = daily.skycon[i]
            val temprature = daily.temperature[i]
            val view = LayoutInflater.from(this).inflate(R.layout.forecast_item,binding.forecastInclude.forecastLayout,false)
            val dateInfo = view.findViewById<TextView>(R.id.dateInfo)
            val skyIcon = view.findViewById<ImageView>(R.id.skyIcon)
            val skyInfo = view.findViewById<TextView>(R.id.skyInfo)
            val temperatureInfo = view.findViewById<TextView>(R.id.temperatureInfo)
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateInfo.text = simpleDateFormat.format(skycon.date)
            val sky = getSky(skycon.value)
            skyIcon.setImageResource(sky.icon)
            skyInfo.text = sky.info
            val tempText = "${temprature.min.toInt()} ~ ${temprature.max.toInt()}°C"
            temperatureInfo.text = tempText
            binding.forecastInclude.forecastLayout.addView(view)
        }

        val lifeIndex = daily.lifeIndex
        binding.lifeIndexInclude.coldRiskText.text = lifeIndex.coldRisk[0].desc
        binding.lifeIndexInclude.dressingText.text = lifeIndex.dressing[0].desc
        binding.lifeIndexInclude.ultravioletText.text = lifeIndex.ultraviolet[0].desc
        binding.lifeIndexInclude.carWashingText.text = lifeIndex.carWashing[0].desc
        binding.weatherLayout.visibility = View.VISIBLE
    }
}