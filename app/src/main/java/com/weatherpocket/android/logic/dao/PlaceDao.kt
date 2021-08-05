package com.weatherpocket.android.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.weatherpocket.android.WeatherPocketApplication
import com.weatherpocket.android.logic.model.Place
import com.weatherpocket.android.logic.model.PlaceResponse

object PlaceDao {

    fun savePlace(place: PlaceResponse){
        sharedPreferences().edit {
            putString("place",Gson().toJson(place))
        }
    }

    fun getSavedPlace(): PlaceResponse {
        val placeJson = sharedPreferences().getString("place","")
        return Gson().fromJson(placeJson,PlaceResponse::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() = WeatherPocketApplication.context.getSharedPreferences("weather_poket",Context.MODE_PRIVATE)

}