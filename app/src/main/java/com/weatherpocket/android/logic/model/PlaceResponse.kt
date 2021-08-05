package com.weatherpocket.android.logic.model

data class PlaceResponse(val name:String,val descContent:String,val location: Location)

data class Location(val lng: String, val lat: String)
