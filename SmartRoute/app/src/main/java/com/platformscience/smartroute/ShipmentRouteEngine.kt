package com.platformscience.smartroute

import com.platformscience.smartroute.data.Shipment
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.RouteResults
import com.platformscience.smartroute.data.RouteRequest
import com.platformscience.smartroute.data.ShipmentScoreMap
import com.platformscience.smartroute.resolver.RouteResolver
import com.platformscience.smartroute.resolver.SSPRouteResolver
import com.platformscience.smartroute.scorer.BSSScorer
import com.platformscience.smartroute.scorer.RouteScorer

object ShipmentRouteEngine {

    /**
     * All methods internal for testability with RouteEngineTest
     */

    internal val resolver: RouteResolver = SSPRouteResolver();
    internal val scorer: RouteScorer = BSSScorer();

    internal fun getScoreMap(drivers:Set<Driver>, shipments:Set<Shipment>): ShipmentScoreMap {
        val routeScores = ShipmentScoreMap();
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
                               routeScores:ShipmentScoreMap): RouteResults {
        return resolver.resolve(drivers, shipments, routeScores);
    }

    fun getShipmentRoutes(request:RouteRequest):RouteResults {
        val routeScores = getScoreMap(request.drivers, request.shipments);
        val routeResults = resolveRoutes(request.drivers,request.shipments,routeScores);
        return routeResults;
    }




}