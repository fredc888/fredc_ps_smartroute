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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ShipmentRouteEngine {

    private val resolver: RouteResolver = SSPRouteResolver();
    private val scorer: RouteScorer = BSSScorer();

    //Internal for unit testing
    internal fun getScoreMap(drivers:Set<Driver>, shipments:Set<Shipment>): ShipmentScoreMap {
        val routeScores = ShipmentScoreMap();
        drivers.forEach{ driver ->
            shipments.forEach { shipment ->
                val score = scorer.score(driver,shipment);
                routeScores.setRouteScore(driver.key,shipment.key,score);
            }
        }
        return routeScores;
    }

    private fun resolveRoutes(drivers:Set<Driver>,
                               shipments:Set<Shipment>,
                               routeScores:ShipmentScoreMap): RouteResults {
        return resolver.resolve(drivers, shipments, routeScores);
    }

    suspend fun getShipmentRoutes(request:RouteRequest): RouteResults {
        return withContext(Dispatchers.IO) {
            _getShipmentRoutes(request)
        }
    }

    fun _getShipmentRoutes(request:RouteRequest): RouteResults {
            val routeScores = getScoreMap(request.drivers, request.shipments);
            return resolveRoutes(request.drivers, request.shipments, routeScores);

    }
}