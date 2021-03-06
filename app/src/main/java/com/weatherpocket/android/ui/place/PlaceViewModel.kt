package com.weatherpocket.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.weatherpocket.android.logic.Repository
import com.weatherpocket.android.logic.dao.PlaceDao
import com.weatherpocket.android.logic.model.Place
import com.weatherpocket.android.logic.model.PlaceResponse


class PlaceViewModel:ViewModel() {
    fun savePlace(place: PlaceResponse) = PlaceDao.savePlace(place)
    fun getSavePlace() = PlaceDao.getSavedPlace()
    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<PlaceResponse>()

    val placeLiveData = Transformations.switchMap(searchLiveData){
        query -> Repository.searchPlaces(query)
    }

    fun searchPlaces(query:String){
        searchLiveData.value = query
    }
}