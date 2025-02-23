package com.platformscience.smartroute.util

import androidx.test.core.app.ApplicationProvider
import org.junit.Test;
import org.junit.Assert.assertEquals

/**
 * Tests for the ShipmentInfoReader singleton
 */
class ShipmentInfoAssetReaderTest {



    @Test
    fun testReader() {
        val request = ShipmentInfoReader.readDefaultShipmentInfoAsset(ApplicationProvider.getApplicationContext());
        assertEquals("Incorrect driver count ", 10,request.drivers.size);
        assertEquals("Incorrect shipment count ", 10,request.shipments.size);
    }
}