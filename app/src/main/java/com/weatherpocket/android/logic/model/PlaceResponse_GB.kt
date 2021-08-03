package com.weatherpocket.android.logic.model

import com.google.gson.annotations.SerializedName

class PlaceResponse_GB(val status:String,val results:List<Place>) {

    data class Place(@SerializedName("formatted_address") val address:String,val name:String)

}