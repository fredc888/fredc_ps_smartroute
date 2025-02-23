package com.platformscience.smartroute

import android.util.Log
import androidx.test.core.app.ApplicationProvider
import com.platformscience.smartroute.util.ShipmentInfoReader
import org.junit.Test;

/**
 * Tests for the ShipmentInfoReader singleton
 */
class RouteEngineTest {



    @Test
    fun testRouteEngine() {
        val request = ShipmentInfoReader.readDefaultShipmentInfoAsset(ApplicationProvider.getApplicationContext());
        val results = RouteEngine.getRoutes(request);

        results.drivers.forEach { driver->
            val shipment  = results.getOptimalShipment(driver);
            Log.d("RouteEngineTest","${driver.name} ==>  ${shipment.address} : Route Score: ${results.getRouteScore(driver,shipment)?.score}" );
        }}
}