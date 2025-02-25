package com.platformscience.smartroute

import org.junit.Test


//Test data imports

import com.platformscience.smartroute.data.RouteRequest
import com.platformscience.smartroute.resolver.SSPRouteResolver

/**
 * Tests for the BSSScorer
 */
class ShipmentRouteEngineTest {

  @Test
  fun testGetScoreMap() {
      val drivers = BssTestData.testDrivers.toSet();
      val shipments = BssTestData.testShipments.toSet();
      val scoreMap = ShipmentRouteEngine.getScoreMap(drivers,shipments);
      System.out.println("Score Map "  + scoreMap);
  }

    @Test
    fun testResolver() {
        val drivers = BssTestData.testDrivers.toSet();
        val shipments = BssTestData.testShipments.toSet();
        val scoreMap = ShipmentRouteEngine.getScoreMap(drivers,shipments);

        val resolver = SSPRouteResolver();
        val scoreMatrix = resolver.createRouteScoreMatrix(drivers,shipments,scoreMap);
        System.out.println("scoreMatrix"  + scoreMatrix.contentDeepToString());
    }

    @Test
    fun testEngine_getRouteIndexes() {
        val drivers = BssTestData.testDrivers.toSet();
        val shipments = BssTestData.testShipments.toSet();

        val routeRequest = RouteRequest(drivers,shipments);
        val results = ShipmentRouteEngine.getShipmentRoutes(routeRequest);
        System.out.println("route results"  + results.routeIndexes.contentToString());
    }

    @Test
    fun testEngine_getRoutes() {
        val drivers = BssTestData.testDrivers.toSet();
        val shipments = BssTestData.testShipments.toSet();

        val routeRequest = RouteRequest(drivers,shipments);
        val results = ShipmentRouteEngine.getShipmentRoutes(routeRequest);
        drivers.forEach { driver->
            val shipment  = results.getOptimalShipment(driver);
            System.out.println(" ${driver.name} ==>  ${shipment.address} : Route Score: ${results.getShipmentScore(driver,shipment)?.score}" );
        }
    }
}