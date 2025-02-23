package com.platformscience.smartroute.util

import org.junit.Test
import com.platformscience.smartroute.util.vowels;
import com.platformscience.smartroute.util.consonants;
import org.junit.Assert.assertEquals

/**
 * Tests for the MathUtil singleton
 */
class StringExTest {
    val drivers = arrayOf(
        "Everado Welch",
        "Orval Mayert",
        "Howard Emmerich",
        "Izaiah Lowe",
        "Monica Hermann",
        "Ellis Wisozk",
        "Noemie Murphy",
        "Cleve Durgan",
        "Murphy Mosciski",
        "Maiser Sose"
        );
    val expectedVowels = intArrayOf(
        5,
        4,
        5,
        6,
        5,
        4,
        6, //Trailing 'y' in at the end considered a vowel
        4,
        5, //Trailing 'y' in first word is considered a vowel
        5

    );
    val expectedConsonants = intArrayOf(
        7,
        7, // The 'y' in "Mayert" should be considered a vowel, but outside the scope currently
        9,
        4,
        8,
        7,
        6,
        7,
        9,
        5
        );
    /**
     * Test the extension function String.vowels()
     */
    @Test
    fun testVowelCount() {
        drivers.forEachIndexed { i, driver ->
            testVowelCount(driver, expectedVowels[i])
        }

    }


    /**
     * Test the extension function String.vowels()
     */
    @Test
    fun testConsonantCount() {
        drivers.forEachIndexed { i, driver ->
            testConsonantCount(driver, expectedConsonants[i])
        }

    }

    private fun testVowelCount(s: String, expectedVowelCount: Int) {
        assertEquals("${s}: Unexpected vowel count", expectedVowelCount,s.vowels());

    }
    private fun testConsonantCount(s: String, expectedConsonantCount: Int) {
        assertEquals("${s}: Unexpected consonant count", expectedConsonantCount,s.consonants());

    }
}