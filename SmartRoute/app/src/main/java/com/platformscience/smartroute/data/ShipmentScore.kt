package com.platformscience.smartroute.data

/**
 * A score for a driver/destination's route
 *
 */
data class ShipmentScore(
    val driverKey: String,
    val shipmentKey: String,
    val score: Double,

    //For debugging only: If debugging is enabled, this field contains
    //key/values properties added by the route engine to help debugging
    //white processing the request
    val debugInfo:Map<String,String>?){
    constructor(driverKey: String, shipmentKey:String, score: Double) : this(
        driverKey=driverKey,
        shipmentKey=shipmentKey,
        score = score,
        debugInfo = null
    );



}
;
// Current implementation just manages a single score value
