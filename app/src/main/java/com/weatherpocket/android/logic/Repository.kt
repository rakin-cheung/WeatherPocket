package com.weatherpocket.android.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.weatherpocket.android.WeatherPocketApplication
import com.weatherpocket.android.logic.model.PlaceResponse
import com.weatherpocket.android.logic.model.PlaceResponse_CN
import com.weatherpocket.android.logic.model.PlaceResponse_GB
import com.weatherpocket.android.logic.network.WeatherPocketNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException
import java.util.*
import kotlin.collections.ArrayList

object Repository {


    fun searchPlaces(query:String) = liveData(Dispatchers.IO){
        val result = try{
            var locale: Locale
            var responseList:List<PlaceResponse>
            if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                locale = WeatherPocketApplication.context.resources.configuration.locales.get(0)
            }else{
                locale = WeatherPocketApplication.context.resources.configuration.locale
            }
            if (locale.toString().contains("zh_CN")) {
                val placeResponse = WeatherPocketNetwork.searchPlaces_CN(query)
                if (placeResponse.status == "1") {
                    //val places = placeResponse.districts
                    responseList = formatedList_CN(placeResponse)
                    Result.success(responseList)
                } else {
                    Result.failure(RuntimeException("response status is ${placeResponse.info}"))
                }
            }else{
                var serachLang = locale.language
                if(serachLang.contains("zh"))
                    serachLang += "-HK"
                val placeResponse = WeatherPocketNetwork.searchPlaces_GB(query,serachLang)
                if (placeResponse.status == "OK") {
                    //val places = placeResponse.result
                    responseList = formatedList_GB(placeResponse)
                    Result.success(responseList)
                } else {
                    Result.failure(RuntimeException("response status is ${placeResponse.status}"))
                }
            }

        }catch (e:Exception){
            Result.failure<List<PlaceResponse>>(e)
        }
        emit(result)
    }

    fun formatedList_CN(cnList:PlaceResponse_CN):List<PlaceResponse>{
        val showList:MutableList<PlaceResponse> = ArrayList<PlaceResponse>()
        for(cn in cnList.districts){
            val place:PlaceResponse = PlaceResponse(cn.name,cn.adcode)
            showList.add(place)
        }
        return showList
    }

    fun formatedList_GB(gbList: PlaceResponse_GB):List<PlaceResponse>{
        val showList:MutableList<PlaceResponse> = ArrayList<PlaceResponse>()
        for(gb in gbList.results){
            val place:PlaceResponse = PlaceResponse(gb.name,gb.address)
            showList.add(place)
        }
        return showList
    }
}