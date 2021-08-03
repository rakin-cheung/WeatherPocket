package com.weatherpocket.android.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.weatherpocket.android.logic.model.PlaceResponse
import com.weatherpocket.android.logic.network.WeatherPocketNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException

object Repository {


    fun searchPlaces(query:String) = liveData(Dispatchers.IO){
        val result = try{

            val placeResponse = WeatherPocketNetwork.searchPlaces(query)
            if(placeResponse.status == "1"){
                val places = placeResponse.districts
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.info}"))
            }

        }catch (e:Exception){
            Result.failure<List<PlaceResponse.Place>>(e)
        }
        emit(result)
    }

}