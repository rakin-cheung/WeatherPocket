package com.weatherpocket.android.logic.model

import com.google.gson.annotations.SerializedName

//接收高德API 接口JSON格式
class PlaceResponse_CN(val status:String,val info:String,val districts:List<Place>) {

    data class Place(val name:String,val adcode:String, val center:String)

}