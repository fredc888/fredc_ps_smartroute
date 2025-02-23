package com.platformscience.smartroute.resolver

import edu.princeton.cs.algs4.AssignmentProblem
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

/**
 * Tests for the Algs4 Library used by the Route Engine
 */
class Algs4Test {

    /**
     * Test the Assignment Problem engine from Algs4.jar
     * 5x5 test
     * from https://cbom.atozmath.com/example/CBOM/Assignment.aspx
     */
    @Test
    fun assignmentTest_5x5() {

        val weight = arrayOf(
            doubleArrayOf(10.0, 5.0, 13.0, 15.0, 16.0),
            doubleArrayOf(3.0, 9.0, 18.0, 13.0, 6.0),
            doubleArrayOf(10.0, 7.0, 2.0, 2.0, 2.0),
            doubleArrayOf(7.0, 11.0, 9.0, 7.0, 12.0),
            doubleArrayOf(7.0, 9.0, 10.0, 4.0, 12.0)
        )
        val n = weight.size

        //Solution: task assignments to each worker
        val expectedAssignments= arrayOf(1,0,4,2,3);

        //Run test
        var assignmentEngine = AssignmentProblem(weight);
        checkAssignmentResults(assignmentEngine,n,expectedAssignments);
    }

    /**
     * Test the Assignment Problem engine from Algs4.jar
\    * 4x4 test
     * https://www.hungarianalgorithm.com/examplehungarianalgorithm.php
     */
    @Test
    fun assignmentTest_4x4() {

        val weight = arrayOf(
            doubleArrayOf(82.0, 83.0, 69.0, 92.0),
            doubleArrayOf(77.0, 37.0, 49.0, 92.0),
            doubleArrayOf(11.0, 69.0, 5.0, 86.0),
            doubleArrayOf(8.0, 9.0, 98.0, 23.0),
        )

        val n = weight.size

        //Solution: task assignments to each worker
        val expectedAssignments= arrayOf(2,1,0,3);

        //Run test
        var assignmentEngine = AssignmentProblem(weight);
        checkAssignmentResults(assignmentEngine,n,expectedAssignments);
    }




    /**
     * Helper function to check the results from the assignment engine with the expected assignments
     * @param assignmentEngine: initialized Assignment Program instance with the specified weights
     * @param n: size of problem (n x n workers and tasks)
     * @param expectedAssignments: expected task assigned to each indexed worker
     */
    private fun checkAssignmentResults( assignmentEngine: AssignmentProblem,
                                        n: Int,
                                        expectedResults: Array<Int>) {
        try {
            assertEquals("Expected assignments length not equal to n ",
                n, expectedResults.size);

            expectedResults.forEachIndexed { i, expectedAssignment ->
                assertEquals("Unexpected task assigned to worker# ${i}",
                     expectedAssignment,assignmentEngine.sol(i));
            }

        } catch (e: IllegalArgumentException) {
            //thrown about AssignmentProblem
            fail("Unexpected Error from AssignmentProgram engine: ${e.message}");
        }

    }

}