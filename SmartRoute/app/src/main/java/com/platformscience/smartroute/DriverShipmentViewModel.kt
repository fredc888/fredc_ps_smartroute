package com.platformscience.smartroute

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platformscience.smartroute.data.RouteResults
import com.platformscience.smartroute.util.ShipmentInfoReader
import kotlinx.coroutines.launch
import java.io.BufferedReader

class DriverShipmentViewModel : ViewModel(){
    private val routeResults: MutableLiveData<RouteResults> = MutableLiveData();


    fun getShipmentRoutes():LiveData<RouteResults>{
        return routeResults;
    }

    fun loadShipmentRoute(shipmentInfoReader:BufferedReader) {
        // Create a new coroutine on the UI thread
        viewModelScope.launch {
            //Read the Shipment Info from the BufferedReader into a RouteRequest object
            val routeRequest = ShipmentInfoReader.readShipmentInfo(shipmentInfoReader);
            val result = ShipmentRouteEngine.getShipmentRoutes(routeRequest)
            routeResults.value=result;
        }
    }
}