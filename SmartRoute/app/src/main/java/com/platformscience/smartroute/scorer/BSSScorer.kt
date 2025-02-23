package com.platformscience.smartroute.scorer

import com.platformscience.smartroute.data.Destination
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.RouteScore
import com.platformscience.smartroute.util.MathUtil
import com.platformscience.smartroute.util.consonants;
import com.platformscience.smartroute.util.vowels;

/**
 * Implements the BSS Scoring Algorithm
 *
 * a) If address length is even, BSS = 1.5 * driver name length
 *    Otherwise, if address is odd, BSS = 1.0 * driver name length
 *
 * b) If address length and driver name length has common factors >1
 * BSS = BSS + 0.5*BSS
 */
class BSSScorer : RouteScorer {
    private val DEBUG=true;
    /**
     * Returns true if 2 integers have common factors other than 1
     * @TODO: this should be moved into a Math utility function in the future.
     */


    override fun score(driver: Driver, destination: Destination): RouteScore {

        var score = 0.0;
        val destinationLength = destination.address.length;
        val driverNameLength = driver.name.length;
        val isDestLengthEven = (destinationLength / 2 == 0);

        var debugInfo: HashMap<String,String>?
        debugInfo = if (DEBUG)? HashMap<String,String>() else null

        //debugInfo.put("isDestLengthEven",isDestLengthEven.toString());

        //Evaluate destination length for even or odd

        //Even length address
        if (isDestLengthEven) {
            //BSS = name's vowel count *1.5
            val vowels = driver.name.vowels();
            debugInfo.put("vowels",vowels.toString());
            score = vowels * 1.5;
        }
        //Odd length address
        else {
            //BSS = name's consonants count * 1
            val consonants = driver.name.consonants();
            debugInfo.put("consonants",consonants.toString());
            score = consonants.toDouble();
        }

        //Evaluate destination and name length for common factor other than 1
        val gcf=MathUtil.greatestCommonFactor(destinationLength, driverNameLength);
        debugInfo.put("gcf",gcf.toString());
        if ( gcf> 1) {
            score += 0.5 * score;
        }

        if (DEBUG) {
            return RouteScore(score, debugInfo);
        } else {
            return RouteScore(score, null);
        }

    }
}