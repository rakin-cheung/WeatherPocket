package com.weatherpocket.android.logic.model

import com.google.gson.annotations.SerializedName

//接收天氣API 接口JSON格式
class PlaceResponse(val status:String,val places:List<Place>) {

    //SerializedName 建立命名規範不一致的映射
    data class Place(val name:String,val location:Location,
    @SerializedName("formatted_address") val address:String)

    data class Location(val lng:String, val lat:String)
}