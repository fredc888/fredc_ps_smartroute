package com.platformscience.smartroute

import com.platformscience.smartroute.data.RouteResults
import com.platformscience.smartroute.data.RouteRequest
import com.platformscience.smartroute.resolver.RouteResolver
import com.platformscience.smartroute.resolver.SSPRouteResolver
import com.platformscience.smartroute.scorer.BSSScorer
import com.platformscience.smartroute.scorer.RouteScorer

object RouteEngine {
    /**
     * Allow dynamic binding of different scorer and resolver algorithms in the future?
     */
    val resolver: RouteResolver = SSPRouteResolver();
    val scorer: RouteScorer = BSSScorer();

    fun resolveRoutes(routeWeights: RouteRequest): RouteResults {
        return resolver.resolve(routeWeights);
    }
}