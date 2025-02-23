package com.platformscience.smartroute.util

import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.Shipment
import org.junit.Test;
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

/**
 * Tests for the ShipmentInfoReader singleton
 */
class ShipmentInfoReaderTest {



    @Test
    fun testReader() {
        val shipmentInfoJson = "{   "+
                "\"shipments\": [ \"address1\" , \"address2\" ]," +
                "\"drivers\": [ \"driver1\" , \"driver2\" ]" +
                " }"
        System.out.println(shipmentInfoJson);
        val request = ShipmentInfoReader.readShipmentInfo(shipmentInfoJson);

        assertEquals("Incorrect driver count ", 2,request.drivers.size);
        assertTrue("Missing driver driver1", request.drivers.contains(Driver("driver1")));
        assertTrue("Missing driver driver2", request.drivers.contains(Driver("driver2")));

        assertEquals("Incorrect shipment count ", 2,request.shipments.size);
        assertTrue("Missing shipment address1", request.shipments.contains(Shipment("address1")));
        assertTrue("Missing shipment adddres2", request.shipments.contains(Shipment("address2")));


    }
}