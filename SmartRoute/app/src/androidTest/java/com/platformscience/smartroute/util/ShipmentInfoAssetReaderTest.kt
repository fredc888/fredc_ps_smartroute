package com.platformscience.smartroute.util

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * Tests for the ShipmentInfoReader singleton
 */
class ShipmentInfoAssetReaderTest {

    /**
     * Test reading Shipping Info JSON from Assets folder
     */
    @Test
    fun testReaderFromAssetFolder() {
        val context:Context = ApplicationProvider.getApplicationContext();
        //Get the default ShippingInfo json

        val assetReader = BufferedReader(InputStreamReader(context.assets.open("shipmentinfo.json")));
        val request = ShipmentInfoReader.readShipmentInfo(assetReader);
        assertEquals("Incorrect driver count ", 10,request.drivers.size);
        assertEquals("Incorrect shipment count ", 10,request.shipments.size);
    }
}