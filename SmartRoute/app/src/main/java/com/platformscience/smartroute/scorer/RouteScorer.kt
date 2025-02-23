package com.platformscience.smartroute.scorer

import com.platformscience.smartroute.data.Destination
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.RouteScore

interface RouteScorer {
    fun score(driver: Driver, destination: Destination): RouteScore;
}