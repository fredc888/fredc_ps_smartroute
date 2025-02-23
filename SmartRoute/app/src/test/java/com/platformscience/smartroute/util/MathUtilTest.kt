package com.platformscience.smartroute.util

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests for the MathUtil singleton
 */
class MathUtilTest {

    /**
     * Test for a GCF between 2 integers other than 1
     *
     */
    @Test
    fun testGCF() {
        testGCF(18,27,9);
        testGCF(27,18,9);

        testGCF(20,12,4);
        testGCF(12,20,4);

        testGCF(54,32,2);
        testGCF(32,54,2);

    }

    /**
     * Tests for no GCF other than 1 between 2 numbers
     */
    @Test
    fun testNoGCF() {
        testGCF(18,7,1);
        testGCF(7,18,1);

        testGCF(11,51,1);
        testGCF(51,11,1);

        testGCF(22,101,1);
        testGCF(101,22,1);

    }

    /**
     * Helper function to calculate GCF between 2 numbers and test if results is the expected value
     */
    private fun testGCF( x:Int,  y:Int,  expectedGcf:Int) {
        val gcf = MathUtil.greatestCommonFactor(x,y);
        assertEquals("Unexpected GCF", expectedGcf,gcf);
    }
}