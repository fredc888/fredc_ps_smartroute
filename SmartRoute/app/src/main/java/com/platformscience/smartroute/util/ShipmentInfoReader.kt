package com.platformscience.smartroute.util

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.platformscience.smartroute.RouteEngine
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.RouteRequest
import com.platformscience.smartroute.data.RouteScore
import com.platformscience.smartroute.data.Shipment
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Parse ShipmentInfo Json file into DataObjects
 */
object ShipmentInfoReader {
    val DEFAULT_ASSET_SHIPMENTINFO="shipmentinfo.json";

    fun readerDefaultShipmentInfoAsset(context:Context):RouteRequest {

        var assetReader:BufferedReader?=null;
        try {
            assetReader = BufferedReader(
                InputStreamReader(context.assets.open(DEFAULT_ASSET_SHIPMENTINFO)));
            val gson = Gson();
            val shipmentInfo: ShipmentInfo = gson.fromJson(assetReader, ShipmentInfo::class.java);
            return toRouteRequest(shipmentInfo);
        } finally {
            try {
                assetReader?.close()
            } catch (e: IOException) {
                println("An error occurred while closing the reader: ${e.message}")
            }
        }
    }

    fun readShipmentInfo(shipmentInfoJson:String):RouteRequest {
        val gson = Gson();
        val shipmentInfo: ShipmentInfo = gson.fromJson(shipmentInfoJson, ShipmentInfo::class.java);
        return toRouteRequest(shipmentInfo);
    }

    private fun toRouteRequest(shipmentInfo:ShipmentInfo):RouteRequest {
        var drivers=ArrayList<Driver>();
        var shipments=ArrayList<Shipment>();

        shipmentInfo.drivers.forEach { name ->
            drivers.add(Driver(name))
        };

        shipmentInfo.shipments.forEach { address ->
            shipments.add(Shipment(address))
        };
        return RouteRequest(drivers.toSet(),shipments.toSet());
    }


}