package com.weatherpocket.android.logic.network

import com.weatherpocket.android.WeatherPocketApplication
import com.weatherpocket.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    //GET請求
    @GET("v2/place?token=${WeatherPocketApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") quert:String):Call<PlaceResponse>
}