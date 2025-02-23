package com.platformscience.smartroute.data

/**
 * A score for a driver/destination's route
 *
 */
data class RouteScore(val score: Double, val debugInfo:Map<String,String>?){
    constructor(score: Double) : this(
        score = score,
        debugInfo = null
    );

}
;
// Current implementation just manages a single score value
