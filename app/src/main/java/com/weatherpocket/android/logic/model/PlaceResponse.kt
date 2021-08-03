package com.weatherpocket.android.logic.model

import com.google.gson.annotations.SerializedName

//接收高德API 接口JSON格式
class PlaceResponse(val status:String,val info:String,val districts:List<Place>) {

    //SerializedName 建立命名規範不一致的映射
    data class Place(val name:String,val adcode:String, val center:String)

    //data class Location(val lng:String, val lat:String)
}