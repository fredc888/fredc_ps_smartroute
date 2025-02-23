package com.platformscience.smartroute.resolver

import com.platformscience.smartroute.data.Shipment
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.RouteResults
import com.platformscience.smartroute.data.RouteScoreMap

/*
 * A RouteResolver uses a provided RouteWeights and finds the optimal route for each driver

 */
interface RouteResolver {
    /**
     * Resolves the optimal route for each driver with specified route weights for each driver
     * @param routeScores
     * @param RouteResults returned optimal results for each driuer
     */
    fun resolve(drivers:Set<Driver>,
                shipments:Set<Shipment>,
                routeScores: RouteScoreMap): RouteResults;
}