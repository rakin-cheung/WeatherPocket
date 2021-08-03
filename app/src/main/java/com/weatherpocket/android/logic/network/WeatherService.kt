package com.weatherpocket.android.logic.network

import com.weatherpocket.android.WeatherPocketApplication
import com.weatherpocket.android.logic.model.DailyResponse
import com.weatherpocket.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("v2.5/${WeatherPocketApplication.TOKEN_CAIYUN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng")lng:String, @Path("lat")lat:String): Call<RealtimeResponse>

    @GET("v2.5/${WeatherPocketApplication.TOKEN_CAIYUN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng")lng:String,@Path("lat")lat:String):Call<DailyResponse>
}