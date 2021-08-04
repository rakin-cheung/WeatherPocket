package com.weatherpocket.android.logic.model

class PlaceResponse(val name:String,val descContent:String) {

    data class Location(val lng: String, val lat: String)
}