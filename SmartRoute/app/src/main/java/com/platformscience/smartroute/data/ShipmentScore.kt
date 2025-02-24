package com.platformscience.smartroute.data

/**
 * A score for a driver/destination's route
 *
 */
data class ShipmentScore(
    val driver: Driver,
    val shipment: Shipment,
    val score: Double,
    val debugInfo:Map<String,String>?){
    constructor(driver: Driver, shipment:Shipment, score: Double) : this(
        driver=driver,
        shipment=shipment,
        score = score,
        debugInfo = null
    );



}
;
// Current implementation just manages a single score value
