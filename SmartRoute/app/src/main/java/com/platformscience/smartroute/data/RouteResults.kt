package com.platformscience.smartroute.data


/**
 * Status returned from RouteEngine to indicate success or failure
 */
enum class RouteResultCode {
    SUCCESS,
    FAIL
}


/**
 * ShippingRoute Result returned by the RouteEngine
 */
data class RouteResults(


    val drivers: Set<Driver>,
    val shipments: Set<Shipment>,
    val shipmentScores: ShipmentScoreMap,
    val resultCode: RouteResultCode = RouteResultCode.SUCCESS,
    val routeIndexes: IntArray? = null,
    val error: Exception? = null
) {
    constructor(drivers:Set<Driver>, shipments:Set<Shipment>,
                routeScores: ShipmentScoreMap, error: Exception) : this(
        drivers=drivers,
        shipments=shipments,
        shipmentScores=routeScores,
        resultCode = RouteResultCode.FAIL,
        error = error,
        routeIndexes = null
    );

    /**
     * Returns of the route table
     */
    val size: Int
        get() {
            return routeIndexes?.size ?: 0;
        }


    /**
     * Returns the shipment score for a driver and shipment
     */
    fun getShipmentScore(driverKey:String, shipmentKey: String):ShipmentScore? {
        return shipmentScores.getRouteScore(driverKey,shipmentKey);
    }

    private fun findDriverIndex(driverKey:String):Int? {
        drivers.forEachIndexed { driverIndex,driver ->
            if (driverKey.equals(driver.key)) {
                return driverIndex;
            }
        }
        return null;
    }
    /**
     * Returns the assigned optimal shipment route for the specified driver
     */
    fun getOptimalShipment(driverKey:String):Shipment? {
        val driverIndex = findDriverIndex(driverKey);
        driverIndex?.let{
            val destinationIndex = getOptimalShipment(driverIndex);
            destinationIndex?.let {
                return shipments.elementAt(destinationIndex);
            }
        }
        return null;
    }

    /**
     * Returns the route of the specified driver index in the driver set
     */
    fun getOptimalShipment(driverIndex: Int): Int? {
        return routeIndexes?.get(driverIndex);
    }

}