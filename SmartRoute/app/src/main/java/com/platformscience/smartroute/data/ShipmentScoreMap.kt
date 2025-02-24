package com.platformscience.smartroute.data

data class ShipmentScoreMap(val scoreMap: LinkedHashMap<Driver,LinkedHashMap<Shipment,ShipmentScore>> =
        LinkedHashMap()) {

    fun setRouteScore(driver:Driver, shipment:Shipment, score:ShipmentScore) {
        var driverRouteScores = scoreMap.get(driver);
        if (driverRouteScores.isNullOrEmpty()) {
            driverRouteScores = LinkedHashMap();
            scoreMap.put(driver,driverRouteScores);
        }
        driverRouteScores.put(shipment,score);
    }

    fun getRouteScore(driver:Driver, shipment: Shipment):ShipmentScore? {
        return scoreMap.get(driver)?.get(shipment);
    }


}
