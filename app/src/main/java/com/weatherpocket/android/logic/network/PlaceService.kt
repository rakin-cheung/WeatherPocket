package com.weatherpocket.android.logic.network

import android.content.res.Resources
import com.weatherpocket.android.R
import com.weatherpocket.android.WeatherPocketApplication
import com.weatherpocket.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceService {

    //GET請求
    @GET("v3/config/district?subdistrict=1&key=${WeatherPocketApplication.TOKEN_LBS}")
    fun searchPlaces(@Query("keywords") query:String):Call<PlaceResponse>
}