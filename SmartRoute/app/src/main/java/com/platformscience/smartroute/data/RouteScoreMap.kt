package com.platformscience.smartroute.data

data class RouteScoreMap(val scoreMap: LinkedHashMap<Driver,LinkedHashMap<Destination,RouteScore>> =
        LinkedHashMap()) {


    fun setRouteScore(driver:Driver, destination:Destination, score:RouteScore) {
        var driverRouteScores = scoreMap.get(driver);
        if (driverRouteScores.isNullOrEmpty()) {
            driverRouteScores = LinkedHashMap<Destination,RouteScore>();
            scoreMap.put(driver,driverRouteScores);
        }
        driverRouteScores.put(destination,score);
    }

    fun getRouteScore(driver:Driver, destination: Destination):RouteScore? {
        return scoreMap.get(driver)?.get(destination);
    }


}
