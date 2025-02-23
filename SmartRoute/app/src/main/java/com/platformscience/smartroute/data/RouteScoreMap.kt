package com.platformscience.smartroute.data

data class RouteScoreMap(val scoreMap: LinkedHashMap<Driver,LinkedHashMap<Shipment,RouteScore>> =
        LinkedHashMap()) {


    fun setRouteScore(driver:Driver, shipment:Shipment, score:RouteScore) {
        var driverRouteScores = scoreMap.get(driver);
        if (driverRouteScores.isNullOrEmpty()) {
            driverRouteScores = LinkedHashMap<Shipment,RouteScore>();
            scoreMap.put(driver,driverRouteScores);
        }
        driverRouteScores.put(shipment,score);
    }

    fun getRouteScore(driver:Driver, shipment: Shipment):RouteScore? {
        return scoreMap.get(driver)?.get(shipment);
    }


}
