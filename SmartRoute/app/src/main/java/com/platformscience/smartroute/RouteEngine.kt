package com.platformscience.smartroute

import com.platformscience.smartroute.data.Shipment
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.RouteResults
import com.platformscience.smartroute.data.RouteRequest
import com.platformscience.smartroute.data.RouteScoreMap
import com.platformscience.smartroute.resolver.RouteResolver
import com.platformscience.smartroute.resolver.SSPRouteResolver
import com.platformscience.smartroute.scorer.BSSScorer
import com.platformscience.smartroute.scorer.RouteScorer

object RouteEngine {

    /**
     * All methods internal for testability with RouteEngineTest
     */

    internal val resolver: RouteResolver = SSPRouteResolver();
    internal val scorer: RouteScorer = BSSScorer();

    internal fun getScoreMap(drivers:Set<Driver>, shipments:Set<Shipment>): RouteScoreMap {
        val routeScores = RouteScoreMap();
        drivers.forEach{ driver ->
            shipments.forEach { shipment ->
                val score = scorer.score(driver,shipment);
                routeScores.setRouteScore(driver,shipment,score);
            }
        }
        return routeScores;
    }

    internal fun resolveRoutes(drivers:Set<Driver>,
                               shipments:Set<Shipment>,
                               routeScores:RouteScoreMap): RouteResults {
        return resolver.resolve(drivers, shipments, routeScores);
    }

    fun getRoutes(request:RouteRequest):RouteResults {
        val routeScores = getScoreMap(request.drivers, request.shipments);
        val routeResults = resolveRoutes(request.drivers,request.shipments,routeScores);
        return routeResults;
    }




}