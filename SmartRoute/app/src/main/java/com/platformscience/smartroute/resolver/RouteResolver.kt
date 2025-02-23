package com.platformscience.smartroute.resolver

import com.platformscience.smartroute.data.RouteResults
import com.platformscience.smartroute.data.RouteRequest

/*
 * A RouteResolver uses a provided RouteWeights and finds the optimal route for each driver

 */
interface RouteResolver {
    /**
     * Resolves the optimal route for each driver with specified route weights for each driver
     * @param RouteWeights
     * @param RouteResults returned optimal results for each driuer
     */
    fun resolve(routeWeights: RouteRequest): RouteResults;
}