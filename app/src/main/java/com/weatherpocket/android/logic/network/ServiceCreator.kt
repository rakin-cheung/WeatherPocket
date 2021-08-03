package com.weatherpocket.android.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private const val BASE_URL_LBS = "https://restapi.amap.com/"
    private const val BASE_URL_CAIYUN = "https://api.caiyunapp.com/"
    private const val BASE_URL_GOOGLE = "https://maps.googleapis.com/"

    private val retrofit_LBS = Retrofit.Builder()
        .baseUrl(BASE_URL_LBS)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofit_CAIYUN = Retrofit.Builder()
        .baseUrl(BASE_URL_CAIYUN)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofit_GOOGLE = Retrofit.Builder()
        .baseUrl(BASE_URL_GOOGLE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>,apiName:String):T{
        when(apiName){
            "LBS" -> return retrofit_LBS.create(serviceClass)
            "CAIYUN" -> return retrofit_CAIYUN.create(serviceClass)
            "GOOGLE" -> return retrofit_GOOGLE.create(serviceClass)
            else -> return retrofit_CAIYUN.create(serviceClass)
        }
    }

    inline fun <reified T> create(apiName:String):T = create(T::class.java,apiName)
}