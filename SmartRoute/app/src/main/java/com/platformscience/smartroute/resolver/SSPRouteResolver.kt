package com.platformscience.smartroute.resolver

import com.platformscience.smartroute.data.Shipment
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.RouteResults
import com.platformscience.smartroute.data.RouteScoreMap
import edu.princeton.cs.algs4.AssignmentProblem

/**
 * RouteResolver implementation based on Successive Shortest Path Algorithm
 * provided by Algs4 library
 */
class SSPRouteResolver : RouteResolver {

    /**
     * For SSP, we negate all the scores from the algorithm
     */
    private fun transformScore(score:Double):Double {
        return score*-1;
    }

    internal fun createRouteScoreMatrix(drivers:Set<Driver>,
                                        shipments:Set<Shipment>,
                                        routeScores: RouteScoreMap): Array<DoubleArray> {
        val scoreMatrix = Array(drivers.size) { DoubleArray(shipments.size) { 0.0 } }
        drivers.forEachIndexed { driverIndex, driver ->
            shipments.forEachIndexed { destinationIndex, destination ->
                val routeScore= routeScores.getRouteScore(driver,destination);
                if (routeScore != null) {
                    scoreMatrix[driverIndex][destinationIndex]=transformScore(routeScore.score);
                }
            }
        }
        return scoreMatrix;
    }


    override fun resolve(drivers:Set<Driver>,
                         shipments:Set<Shipment>,
                         routeScores: RouteScoreMap): RouteResults {
        try {
            val scoreMatrix = createRouteScoreMatrix(drivers,shipments,routeScores);

            val assignmentEngine = AssignmentProblem(scoreMatrix);
            val routeSize  = shipments.size;
            val routes = IntArray(routeSize);
            for (i in 0 until routeSize) {
                routes[i] = assignmentEngine.sol(i);
            }

            return RouteResults(
                drivers=drivers,
                shipments=shipments,
                routeScores=routeScores,
                routeIndexes = routes);
        } catch (e: IllegalArgumentException) {
            return RouteResults(
                drivers=drivers,
                shipments=shipments,
                routeScores=routeScores,
                error = e);
        }
    }
}