package com.platformscience.smartroute

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platformscience.smartroute.data.RouteRequest
import com.platformscience.smartroute.data.RouteResults
import com.platformscience.smartroute.util.ShipmentInfoReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.net.URL

class DriverShipmentViewModel : ViewModel() {
    private val routeResults: MutableLiveData<RouteResults> = MutableLiveData();
    private val developerModeOn: MutableLiveData<Boolean> = MutableLiveData();

    init {
        developerModeOn.value = false;
    }

    fun getDeveloperMode(): LiveData<Boolean> {
        return developerModeOn;
    }

    fun getShipmentRoutes(): LiveData<RouteResults> {
        return routeResults;
    }

    fun loadShipmentRoute(url: URL) {
        viewModelScope.launch {
            try {
                val routeRequest = readShipmentInfoFromUrl(url);
                val result = ShipmentRouteEngine.getShipmentRoutes(routeRequest)
                routeResults.value = result;
            } catch (e: IOException) {
                Log.e("DriverShipmentViewModel", "loadShipmentRoute error", e);
            }
        }
    }


    fun loadShipmentRoute(shipmentInfoReader: BufferedReader) {
        // Create a new coroutine on the UI thread
        viewModelScope.launch {
            try {
                //Read the Shipment Info from the BufferedReader into a RouteRequest object
                val routeRequest = ShipmentInfoReader.readShipmentInfo(shipmentInfoReader);
                val result = ShipmentRouteEngine.getShipmentRoutes(routeRequest)
                routeResults.value = result;
            } catch (e: IOException) {
                Log.e("DriverShipmentViewModel", "loadShipmentRoute error", e);
            }
        }
    }

    fun setDeveloperModeOn(devModeOn: Boolean) {
        developerModeOn.value = devModeOn
    }

    private suspend fun readShipmentInfoFromUrl(url: URL): RouteRequest {
        return withContext(Dispatchers.IO) {
            ShipmentInfoReader.readShipmentInfo(url);
        }
    }
}