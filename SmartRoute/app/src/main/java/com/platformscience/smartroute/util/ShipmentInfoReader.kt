package com.platformscience.smartroute.util

import android.content.Context
import com.google.gson.Gson
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.RouteRequest
import com.platformscience.smartroute.data.Shipment
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

/**
 * Parse ShipmentInfo Json file into DataObjects
 */
object ShipmentInfoReader {
    val DEFAULT_ASSET_SHIPMENTINFO="shipmentinfo.json";

    fun readShipmentInfo(url:URL):RouteRequest {
        val reader = BufferedReader(InputStreamReader(url.openStream()));
        return readShipmentInfo(reader);
    }

    fun readDefaultShipmentInfoAsset(context:Context):RouteRequest {
        val assetReader = BufferedReader(InputStreamReader(context.assets.open(DEFAULT_ASSET_SHIPMENTINFO)));
        return readShipmentInfo(assetReader);
    }

    /*
    fun readShipmentInfo(file:File): RouteRequest {
        return readShipmentInfo(BufferedReader(file.reader()));
    }
    */

    private fun readShipmentInfo(shipmentInfoReader:BufferedReader):RouteRequest{
        try {
             val gson = Gson();
            val shipmentInfo: ShipmentInfo = gson.fromJson(shipmentInfoReader, ShipmentInfo::class.java);
            return toRouteRequest(shipmentInfo);
        } finally {
            try {
                shipmentInfoReader?.close()
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