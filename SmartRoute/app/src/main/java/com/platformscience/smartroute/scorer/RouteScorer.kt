package com.platformscience.smartroute.scorer

import com.platformscience.smartroute.data.Shipment
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.RouteScore

interface RouteScorer {
    fun score(driver: Driver, shipment: Shipment): RouteScore;
}