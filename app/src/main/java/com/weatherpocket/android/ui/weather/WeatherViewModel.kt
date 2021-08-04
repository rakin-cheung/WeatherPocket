package com.weatherpocket.android.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.weatherpocket.android.logic.Repository
import com.weatherpocket.android.logic.model.PlaceResponse
import com.weatherpocket.android.logic.model.Weather

class WeatherViewModel: ViewModel() {

    private val locationLiveData = MutableLiveData<PlaceResponse.Location>()

    val locationLng = ""
    val locationLat = ""
    var placeName = ""
    val weatherLiveData = Transformations.switchMap(locationLiveData){
        location -> Repository.refreshWeather(location.lng,location.lat)
    }

    fun refreshWeather(lng:String,lat:String){
        locationLiveData.value = PlaceResponse.Location(lng, lat)
    }
}