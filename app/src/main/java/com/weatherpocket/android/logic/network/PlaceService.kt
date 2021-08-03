package com.weatherpocket.android.logic.network

import android.content.res.Resources
import com.weatherpocket.android.R
import com.weatherpocket.android.WeatherPocketApplication
import com.weatherpocket.android.logic.model.PlaceResponse_CN
import com.weatherpocket.android.logic.model.PlaceResponse_GB
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceService {

    //GET請求
    @GET("v3/config/district?subdistrict=1&key=${WeatherPocketApplication.TOKEN_LBS}")
    fun searchPlaces_CN(@Query("keywords") query:String):Call<PlaceResponse_CN>

    @GET("maps/api/place/textsearch/json?key=${WeatherPocketApplication.TOKEN_GOOGLE}&type=[street_address]")
    fun searchPlaces_GB(@Query("query")query:String,@Query("language")lang:String):Call<PlaceResponse_GB>
}