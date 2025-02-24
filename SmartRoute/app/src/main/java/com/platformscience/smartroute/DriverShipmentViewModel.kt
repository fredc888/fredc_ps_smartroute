package com.platformscience.smartroute

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platformscience.smartroute.data.RouteResults

class DriverShipmentViewModel : ViewModel(){
    private val routeResults: MutableLiveData<RouteResults> = MutableLiveData();

    fun updateShipmentRoutes(routeResults: RouteResults?=null) {
        this.routeResults.value = routeResults
    }

    fun getShipmentRoutes():LiveData<RouteResults>{
        return routeResults;
    }
}