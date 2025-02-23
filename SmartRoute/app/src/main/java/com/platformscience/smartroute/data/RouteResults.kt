package com.platformscience.smartroute.data


enum class RouteResultCode {
    SUCCESS,
    FAIL
}

/**
 * @pqram resultCode:
 */
data class RouteResults(
    val drivers: Set<Driver>,
    val shipments: Set<Shipment>,
    val routeScores: RouteScoreMap,
    val resultCode: RouteResultCode = RouteResultCode.SUCCESS,
    val routeIndexes: IntArray? = null,
    val error: Exception? = null
) {
    constructor(drivers:Set<Driver>, shipments:Set<Shipment>,
                routeScores: RouteScoreMap, error: Exception) : this(
        drivers=drivers,
        shipments=shipments,
        routeScores=routeScores,
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

    fun getRouteScore(driver:Driver, shipment: Shipment):RouteScore? {
        return routeScores.getRouteScore(driver,shipment);
    }

    fun getOptimalShipment(driver:Driver):Shipment {
        val driverIndex = drivers.indexOf(driver);
        val destinationIndex = getOptimalShipment(driverIndex);
        return shipments.elementAt(destinationIndex);
    }

    /**
     * Returns the route of the specified driver
     */
    fun getOptimalShipment(driverIndex: Int): Int {
        var result= routeIndexes?.get(driverIndex);
        if (result == null) {
            result = -1;
        }
        return result;
    }

}