package com.platformscience.smartroute

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.platformscience.smartroute.data.RouteResults
import com.platformscience.smartroute.data.RouteRequest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RouteEngineTest {
    private val DEBUG_TAG = RouteEngineTest::class.simpleName;
    private val DEBUG = false;

    /**
     * Test the route resolver with the 5x5 test
     * from https://cbom.atozmath.com/example/CBOM/Assignment.aspx
     */
    @Test
    fun testRouteResolver_5x5() {
        val routeWeights = RouteRequest(
            weights =
            arrayOf(
                doubleArrayOf(10.0, 5.0, 13.0, 15.0, 16.0),
                doubleArrayOf(3.0, 9.0, 18.0, 13.0, 6.0),
                doubleArrayOf(10.0, 7.0, 2.0, 2.0, 2.0),
                doubleArrayOf(7.0, 11.0, 9.0, 7.0, 12.0),
                doubleArrayOf(7.0, 9.0, 10.0, 4.0, 12.0)
            )
        );

        val routeResults = RouteEngine.resolveRoutes(routeWeights);
        val expectedResults = intArrayOf(1, 0, 4, 2, 3);
        checkRouteResults(routeResults, expectedResults);
    }

    /**
     * Test the route resolver with the 5x5 test
     * from https://cbom.atozmath.com/example/CBOM/Assignment.aspx
     */
    @Test
    fun testRouteResolver_4x4() {
        val routeWeights = RouteRequest(
            weights =
            arrayOf(
                doubleArrayOf(82.0, 83.0, 69.0, 92.0),
                doubleArrayOf(77.0, 37.0, 49.0, 92.0),
                doubleArrayOf(11.0, 69.0, 5.0, 86.0),
                doubleArrayOf(8.0, 9.0, 98.0, 23.0),
            )
        );

        val routeResults = RouteEngine.resolveRoutes(routeWeights);
        val expectedResults = intArrayOf(2, 1, 0, 3);
        checkRouteResults(routeResults, expectedResults);
    }

    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.platformscience.smartroute", appContext.packageName)
    }


    private fun checkRouteResults(
        routeResults: RouteResults,
        expectedResults: IntArray
    ) {
        try {
            if (DEBUG) {
                val buffer = StringBuilder();
                routeResults.printRoutes(buffer);
                Log.d(DEBUG_TAG, buffer.toString());
            }

            assertEquals(
                "Expected assignments length not equal to n ",
                expectedResults.size, routeResults.size
            );

            expectedResults.forEachIndexed { i, expectedResult ->
                assertEquals(
                    "Unexpected task assigned to worker# ${i}",
                    expectedResult, routeResults.getRoute(i)
                );
            }

        } catch (e: IllegalArgumentException) {
            //thrown from AssignmentProblem
            fail("Unexpected Error from AssignmentProgram engine: ${e.message}");
        }

    }
}