package com.weatherpocket.android.logic.network

import com.weatherpocket.android.R
import com.weatherpocket.android.logic.model.PlaceResponse_CN
import com.weatherpocket.android.logic.model.PlaceResponse_GB
import com.weatherpocket.android.logic.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object WeatherPocketNetwork {

    //代理
    private val placeService_CN = ServiceCreator.create<PlaceService>("LBS")
    private val placeService_GB = ServiceCreator.create<PlaceService>("GOOGLE")
    private val weatherServicece = ServiceCreator.create<WeatherService>()

    //代理調用 PlaceService方法
    suspend fun searchPlaces_CN(query:String):PlaceResponse_CN = placeService_CN.searchPlaces_CN(query).await()
    suspend fun searchPlaces_GB(query:String,lang:String):PlaceResponse_GB = placeService_GB.searchPlaces_GB(query,lang).await()

    suspend fun getDailyWeather(lng:String,lat:String) = weatherServicece.getDailyWeather(lng,lat).await()
    suspend fun getRealtimeWeather(lng:String,lat:String) = weatherServicece.getRealtimeWeather(lng,lat).await()


        private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine { continuation ->
            enqueue(object:Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if(body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}