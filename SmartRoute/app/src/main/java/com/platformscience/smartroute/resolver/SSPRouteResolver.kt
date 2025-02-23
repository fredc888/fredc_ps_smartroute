package com.platformscience.smartroute.resolver

import com.platformscience.smartroute.data.Destination
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

    private fun createRouteScoreMatrix(drivers:Set<Driver>,
                                       destinations:Set<Destination>,
                                       routeScores: RouteScoreMap): Array<DoubleArray> {
        val scoreMatrix = Array(drivers.size) { DoubleArray(destinations.size) { 0.0 } }
        drivers.forEachIndexed { driverIndex, driver ->
            destinations.forEachIndexed { destinationIndex, destination ->
                val routeScore= routeScores.getRouteScore(driver,destination);
                if (routeScore != null) {
                    scoreMatrix[driverIndex][destinationIndex]=transformScore(routeScore.score);
                }
            }
        }
        return scoreMatrix;
    }


    override fun resolve( drivers:Set<Driver>,
                          destinations:Set<Destination>,
                          routeScores: RouteScoreMap): RouteResults {
        try {
            val scoreMatrix = createRouteScoreMatrix(drivers,destinations,routeScores);

            val assignmentEngine = AssignmentProblem(scoreMatrix);
            val routeSize  = destinations.size;
            val routes = IntArray(routeSize);
            for (i in 0 until routeSize) {
                routes[i] = assignmentEngine.sol(i);
            }

            return RouteResults(routeScores=routeScores, routes = routes);
        } catch (e: IllegalArgumentException) {
            return RouteResults(routeScores=routeScores, error = e);
        }
    }
}