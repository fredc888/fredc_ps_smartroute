package com.platformscience.smartroute.scorer

import com.platformscience.smartroute.data.Shipment
import com.platformscience.smartroute.data.Driver

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test


//Test data imports
import com.platformscience.smartroute.BssTestData.driverTestData;
import com.platformscience.smartroute.BssTestData.shipmentTestData;

import com.platformscience.smartroute.BssTestData;
import com.platformscience.smartroute.BssTestData.KEY_GCF
import com.platformscience.smartroute.data.RouteScore
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

/**
 * Tests for the BSSScorer
 */
class BssScorerTest {



    /**
     * Test scorer debug flag to enable/disable debugging info
     */
    @Test
    fun testScorerDebugFlag() {
        val scorer = BSSScorer();

        //Get a random single driver and shipment, doesn't matter
        val driver = Driver("");
        val shipment = Shipment("");

        //Test debugging turned off by default
        val scoreNoDebug = scorer.score(driver, shipment);
        assertNull("Scorer should not have debugInfo", scoreNoDebug.debugInfo);

        //Test debugging turned on
        scorer.DEBUG = true;
        val scoreWithDebug = scorer.score(driver, shipment);
        assertNotNull("Scorer should have debugInfo", scoreWithDebug.debugInfo);
    }


    /**
     * Test scorer with test data 1
     */
    @Test
    fun testScorer() {
        val scorer = BSSScorer();
        //Turn on debugger
        scorer.DEBUG = true;



        for ((driverIndex,driverKey) in driverTestData.keys.withIndex()) {
            for ((shipmentIndex, shipmentKey) in shipmentTestData.keys.withIndex()) {
                val driver = Driver(driverKey);
                val shipment = Shipment(shipmentKey);
                val score = scorer.score(driver, shipment);
                System.out.println("Driver and Shipment Index ${driverIndex}: ${shipmentIndex}")
                checkScore(driver, shipment, score);
            }
        }
    }

    fun checkScore(driver:Driver, shipment:Shipment, score:RouteScore) {
        //Check the score debug info is available
        System.out.println("debugInfo " + score.debugInfo);
        assertNotNull("scorer debug info not available", score.debugInfo);

        //Load expected results.
        val driverExpectedResult = driverTestData.get(driver.name);
        val shipmentExpectedResult = shipmentTestData.get(shipment.address);
        assertNotNull(driverExpectedResult);
        assertNotNull(shipmentExpectedResult);

        //Check shipment expected property values
        assertEquals ("Shipment ${shipment.address}:  unexpected shipment length",
            shipmentExpectedResult!!.get(BssTestData.KEY_SHIPMENT_LENGTH),
            score.debugInfo?.get(BSSScorer.DEBUG_DEST_LENGTH));
        assertEquals ("Shipment ${shipment.address}:  unexpected shipment isEven flag",
            shipmentExpectedResult!!.get(BssTestData.KEY_SHIPMENT_LENGTHEVEN),
            score.debugInfo?.get(BSSScorer.DEBUG_DEST_LENGTHEVEN));
System.out.println();

        //Based on shipment odd/even rule, check if driver vowel or consonant dependency is set
        if ("true".equals(shipmentExpectedResult?.get(BssTestData.KEY_SHIPMENT_LENGTHEVEN))) {
            //Check driver's vowel count
            assertTrue("Driver ${driver.name}: ${BSSScorer.DEBUG_DRIVER_VOWELS} not set",
                isDebugInfoPropertySet(BSSScorer.DEBUG_DRIVER_VOWELS,score));
            assertTrue("Driver ${driver.name}: ${BSSScorer.DEBUG_DRIVER_CONSONANTS} should NOT be set",
                !isDebugInfoPropertySet(BSSScorer.DEBUG_DRIVER_CONSONANTS,score));

            assertEquals("Driver ${driver.name}: unexpected vowel count",
                driverExpectedResult!!.get(BssTestData.KEY_DRIVER_VOWELS),
                score.debugInfo?.get(BSSScorer.DEBUG_DRIVER_VOWELS)) ;

        } else {
            //Check driver's consonant count
            assertTrue("Driver ${driver.name}: ${BSSScorer.DEBUG_DRIVER_CONSONANTS} not set",
                isDebugInfoPropertySet(BSSScorer.DEBUG_DRIVER_CONSONANTS,score));
            assertTrue("Driver ${driver.name}: ${BSSScorer.DEBUG_DRIVER_VOWELS} should NOT be set",
                !isDebugInfoPropertySet(BSSScorer.DEBUG_DRIVER_VOWELS,score));
            assertEquals("Driver ${driver.name}: unexpected consonant count",
                driverExpectedResult!!.get(BssTestData.KEY_DRIVER_CONSONANTS),
                score.debugInfo?.get(BSSScorer.DEBUG_DRIVER_CONSONANTS));

        }

        //Check gcf
        val expectedGCF = BssTestData.getMappedData(driver.name,shipment.address, KEY_GCF);
        val actualGCF = score.debugInfo?.get(BSSScorer.DEBUG_GCF);
        System.out.println("Getting GCF for Driver ${driver.name}, Shipment ${shipment.address} ");
        assertEquals ("Driver ${driver.name}, Shipment ${shipment.address} unexpected GCF", expectedGCF, actualGCF);


    }

    private fun isDebugInfoPropertySet(propertyKey:String, score:RouteScore) : Boolean{
        return !score.debugInfo!!.get(propertyKey).isNullOrEmpty();
    }



}