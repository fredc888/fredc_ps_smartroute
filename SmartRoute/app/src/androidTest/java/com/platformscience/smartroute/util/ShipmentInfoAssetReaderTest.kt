package com.platformscience.smartroute.util

import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.Shipment
import org.junit.Test;
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

/**
 * Tests for the ShipmentInfoReader singleton
 */
class ShipmentInfoAssetReaderTest {



    @Test
    fun testReader() {
        val request = ShipmentInfoReader.readerDefaultShipmentInfoAsset(ApplicationProvider.getApplicationContext());
        assertEquals("Incorrect driver count ", 10,request.drivers.size);
        assertEquals("Incorrect shipment count ", 10,request.shipments.size);
    }
}