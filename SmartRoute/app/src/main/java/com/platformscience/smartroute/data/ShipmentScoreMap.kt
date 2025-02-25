package com.platformscience.smartroute.data

/**
 * Manages a the shipment scores for all driver and shipment combintions.
 * This object is optimized for retrivals based on the order of
 * driver->shipment
 */
data class ShipmentScoreMap(val scoreMap: LinkedHashMap<String,LinkedHashMap<String,ShipmentScore>> =
        LinkedHashMap()) {

    fun setRouteScore(driverKey:String, shipmentKey:String, score:ShipmentScore) {
        var driverRouteScores = scoreMap.get(driverKey);
        if (driverRouteScores.isNullOrEmpty()) {
            driverRouteScores = LinkedHashMap();
            scoreMap.put(driverKey,driverRouteScores);
        }
        driverRouteScores.put(shipmentKey,score);
    }

    fun getRouteScore(driverKey:String, shipmentKey: String):ShipmentScore? {
        return scoreMap.get(driverKey)?.get(shipmentKey);
    }


}
