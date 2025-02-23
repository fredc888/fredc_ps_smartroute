package com.platformscience.smartroute.util

import com.platformscience.smartroute.BssTestData
import org.junit.Test
import com.platformscience.smartroute.util.vowels;
import com.platformscience.smartroute.util.consonants;
import org.junit.Assert.assertEquals

//Test data imports
import com.platformscience.smartroute.BssTestData.driverTestData;

/**
 * Tests for the MathUtil singleton
 */
class StringExTest {

    /**
     * Test the extension function String.vowels()
     */
    @Test
    fun testVowelCount() {
        for (driver in driverTestData.keys) {
            testVowelCount(driver, driverTestData.get(driver)!!.get(BssTestData.KEY_DRIVER_VOWELS)!!.toInt());
        }
    }


    /**
     * Test the extension function String.vowels()
     */
    @Test
    fun testConsonantCount() {
        for (driver in driverTestData.keys) {
            testConsonantCount(driver, driverTestData.get(driver)!!.get(BssTestData.KEY_DRIVER_CONSONANTS)!!.toInt());

        }
    }

    private fun testVowelCount(s: String, expectedVowelCount: Int) {
        assertEquals("${s}: Unexpected vowel count", expectedVowelCount,s.vowels());

    }
    private fun testConsonantCount(s: String, expectedConsonantCount: Int) {
        assertEquals("${s}: Unexpected consonant count", expectedConsonantCount,s.consonants());

    }
}