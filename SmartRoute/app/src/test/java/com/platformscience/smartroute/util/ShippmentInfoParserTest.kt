package com.platformscience.smartroute.util

import com.google.gson.Gson
import org.junit.Test

/**
 * Tests for the MathUtil singleton
 */
class ShippmentInfoParserTest {

    @Test
    fun testParser() {
        val shipmentInfoJson = "{   "+
                "\"shipments\": [ \"address1\" , \"address2\" ]," +
                "\"drivers\": [ \"driver1\" , \"driver2\" ]" +
                " }"
        System.out.println(shipmentInfoJson);
        val parser = Gson();
        val shipmentInfo: ShipmentInfo = parser.fromJson(shipmentInfoJson, ShipmentInfo::class.java)
        System.out.println("shipmentInfo Drivers" + shipmentInfo.drivers);
        System.out.println("shipmentInfo Destination" + shipmentInfo.shipments);

    }
}