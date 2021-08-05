package com.weatherpocket.android.logic.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse_GB(val status:String,val results:List<Place>)

data class Place(@SerializedName("formatted_address") val address:String,val name:String, val geometry:Geometry)

data class Geometry(val location: Locate)

data class Locate(val lat:Float,val lng:Float)
