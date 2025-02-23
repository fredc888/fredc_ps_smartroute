package com.platformscience.smartroute.data


enum class RouteResultCode {
    SUCCESS,
    FAIL
}

/**
 * @pqram resultCode:
 */
data class RouteResults(
    val routeScores: RouteScoreMap,
    val resultCode: RouteResultCode = RouteResultCode.SUCCESS,
    val routes: IntArray? = null,
    val error: Exception? = null
) {
    constructor(routeScores: RouteScoreMap, error: Exception) : this(
        routeScores=routeScores,
        resultCode = RouteResultCode.FAIL,
        error = error,
        routes = null
    );

    /**
     * Returns of the route table
     */
    val size: Int
        get() {
            return routes?.size ?: 0;
        }

    /**
     * Returns the route of the specified driver
     */
    fun getRoute(index: Int): Int? {
        return routes?.get(index);
    }

    /**
     * Print the list of routes to the specified buffer for debugging
     *
     */
    fun printRoutes(buffer: StringBuilder) {
        routes?.forEachIndexed { i, route ->
            buffer.append("${i} : ${route}\n")
        }
    }
}