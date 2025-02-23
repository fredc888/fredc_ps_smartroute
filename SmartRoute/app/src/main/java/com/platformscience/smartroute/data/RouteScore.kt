package com.platformscience.smartroute.data

/**
 * A score for a driver/destination's route
 *
 */
data class RouteScore(
                        val driver: Driver,
                        val destination: Destination,
                        val score: Double,
                        val debugInfo:Map<String,String>?){
    constructor(driver: Driver,destination:Destination,score: Double) : this(
        driver=driver,
        destination=destination,
        score = score,
        debugInfo = null
    );



}
;
// Current implementation just manages a single score value
