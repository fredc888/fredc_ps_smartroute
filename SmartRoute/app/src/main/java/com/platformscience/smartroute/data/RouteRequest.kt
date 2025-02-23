package com.platformscience.smartroute.data

data class RouteRequest(val drivers: Set<Driver>, val destinations: Set<Destination>) {
    public val routeWeights = Array(drivers.size) { DoubleArray(destinations.size) }

    /**
     * Returns the number of drivers
     */
    val driverCount: Int
        get() {
            return drivers.size;
        }

    /**
     * Returns the number of destinations
     *
     */
    val destinationCount: Int
        get() {
            return drivers.size;
        }

    fun getRouteWeight(driver: Driver, destination: Destination): Double? {
        val driverIndex = drivers.indexOf(driver);
        val destinationIndex = destinations.indexOf(destination);
        return getRouteWeight(driverIndex, destinationIndex);

    }

    fun getRouteWeight(driverIndex: Int, destinationIndex: Int): Double? {
        return if ((driverIndex in 0 until driverCount)
            && (destinationIndex in 0 until destinationCount)
        ) {
            routeWeights[driverIndex][destinationIndex];
        } else {
            null;
        }
    }

    fun setRouteWeight(driver: Driver, destination: Destination, routeWeight: Double) {
        val driverIndex = drivers.indexOf(driver);
        val destinationIndex = destinations.indexOf(destination);
        setRouteWeight(driverIndex, destinationIndex, routeWeight)
    }

    fun setRouteWeight(driverIndex: Int, destinationIndex: Int, routeWeight: Double) {
        if ((driverIndex in 0 until driverCount)
            && (destinationIndex in 0 until destinationCount)
        ) {
            routeWeights[driverIndex][destinationIndex] = routeWeight;
        } else {
            //TODO: Error handle
        }

        fun applyRouteWeightTransform(lambda: (Double) -> Double) {
            routeWeights.forEachIndexed { driverIndex, driver ->
                driver.forEachIndexed({ destinationIndex, destination ->
                    val weight = routeWeights[driverIndex][destinationIndex];
                    routeWeights[driverIndex][destinationIndex] = lambda(weight);
                })
            }
        }
    }

}
