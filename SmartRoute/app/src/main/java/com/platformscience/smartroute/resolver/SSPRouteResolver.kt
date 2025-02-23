package com.platformscience.smartroute.resolver

import com.platformscience.smartroute.data.RouteResults
import com.platformscience.smartroute.data.RouteRequest
import edu.princeton.cs.algs4.AssignmentProblem

/**
 * RouteResolver implementation based on Successive Shortest Path Algorithm
 * provided by Algs4 library
 */
class SSPRouteResolver : RouteResolver {

    override fun resolve(routeWeights: RouteRequest): RouteResults {
        try {
            val assignmentEngine = AssignmentProblem(routeWeights.routeWeights);
            val routeSize = routeWeights.destinationCount;
            val routes = IntArray(routeSize);
            for (i in 0..routeSize - 1) {
                routes[i] = assignmentEngine.sol(i);
            }

            return RouteResults(routes = routes);
        } catch (e: IllegalArgumentException) {
            return RouteResults(error = e);
        }
    }
}