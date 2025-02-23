package com.platformscience.smartroute.util

/**
 * Helper singleton for common math operations
 */
object MathUtil {

    /**
     * Find the greatest common factor of 2 integers
     * @param x first integer
     * @param y second integer
     * @return the GCF of to integers
     */
    fun greatestCommonFactor(x: Int, y: Int): Int {
        var i = 1
        var gcf = 1
        while (i <= x && i <= y) {
            // Checks if i is factor of both integers
            if (x % i == 0 && y % i == 0)
                gcf = i
            ++i
        }
        return gcf;
    }
}