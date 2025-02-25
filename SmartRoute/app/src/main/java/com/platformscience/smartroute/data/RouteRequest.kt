package com.platformscience.smartroute.data

/**
 * Request object used by RouteEngine to calculate Shipment Routes
 */
data class RouteRequest(val drivers: Set<Driver>,
                        val shipments: Set<Shipment>) {



}
