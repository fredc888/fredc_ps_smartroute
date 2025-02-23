package com.platformscience.smartroute

import com.platformscience.smartroute.data.Destination
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.RouteResults
import com.platformscience.smartroute.data.RouteRequest
import com.platformscience.smartroute.data.RouteScore
import com.platformscience.smartroute.data.RouteScoreMap
import com.platformscience.smartroute.resolver.RouteResolver
import com.platformscience.smartroute.resolver.SSPRouteResolver
import com.platformscience.smartroute.scorer.BSSScorer
import com.platformscience.smartroute.scorer.RouteScorer

object RouteEngine {

    /**
     * Allow dynamic binding of different scorer and resolver algorithms in the future?
     */
    internal val resolver: RouteResolver = SSPRouteResolver();
    internal val scorer: RouteScorer = BSSScorer();

    private fun getScoreMap(drivers:Set<Driver>, destinations:Set<Destination>): RouteScoreMap {
        val routeScores = RouteScoreMap();
        drivers.forEach{ driver ->
            destinations.forEach { destination ->
                val score = scorer.score(driver,destination);
                routeScores.setRouteScore(driver,destination,score);
            }
        }
        return routeScores;
    }

    private fun resolveRoutes(drivers:Set<Driver>,
                              destinations:Set<Destination>,
                              routeScores:RouteScoreMap): RouteResults {
        return resolver.resolve(drivers, destinations, routeScores);
    }

    fun getRoutes(request:RouteRequest):RouteResults {
        val routeScores = getScoreMap(request.drivers, request.destinations);
        val routeResults = resolveRoutes(request.drivers,request.destinations,routeScores);
        return routeResults;
    }




}