package com.platformscience.smartroute.scorer

import com.platformscience.smartroute.data.Shipment
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.ShipmentScore
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
    //Debugging turned on/off
    internal var DEBUG = false;

    companion object {
        internal val DEBUG_DEST_LENGTHEVEN = "dest.lengthEven";
        internal val DEBUG_DEST_LENGTH = "dest.length";
        internal val DEBUG_DRIVER_LENGTH = "driver.length";
        internal val DEBUG_DRIVER_VOWELS = "driver.vowels";
        internal val DEBUG_DRIVER_CONSONANTS = "driver.consonants";
        internal val DEBUG_GCF = "gcf";
        internal val DEBUG_SCORE_0 = "score.0";
        internal val DEBUG_SCORE = "score";

    }


    /**
     * Returns true if 2 integers have common factors other than 1
     * @TODO: this should be moved into a Math utility function in the future.
     */


    override fun score(driver: Driver, shipment: Shipment): ShipmentScore {
        //debugInfo is for debugging and troubleshooting
        //and conditionally assigned if debugging is turned on

        val debugInfo: HashMap<String,String>? = if (DEBUG) HashMap<String,String>() else null;

        var score = 0.0;
        val destinationLength = shipment.address.length;
        val driverNameLength = driver.name.length;
        val isDestLengthEven = (destinationLength % 2 == 0);

        debugInfo?.put(DEBUG_DRIVER_LENGTH,driverNameLength.toString());
        debugInfo?.put(DEBUG_DEST_LENGTH, destinationLength.toString());
        debugInfo?.put(DEBUG_DEST_LENGTHEVEN,isDestLengthEven.toString());

        //Evaluate destination length for even or odd

        //Even length address
        if (isDestLengthEven) {
            //BSS = name's vowel count *1.5
            val vowels = driver.name.vowels();
            debugInfo?.put(DEBUG_DRIVER_VOWELS,vowels.toString());
            score = vowels * 1.5;
        }
        //Odd length address
        else {
            //BSS = name's consonants count * 1
            val consonants = driver.name.consonants();
            debugInfo?.put(DEBUG_DRIVER_CONSONANTS,consonants.toString());
            score = consonants.toDouble();
        }
        debugInfo?.put(DEBUG_SCORE_0,score.toString());
        //Evaluate destination and name length for common factor other than 1
        val gcf=MathUtil.greatestCommonFactor(destinationLength, driverNameLength);
        debugInfo?.put(DEBUG_GCF,gcf.toString());
        if ( gcf> 1) {
            score += 0.5 * score;
        }
        debugInfo?.put(DEBUG_SCORE,score.toString());

        return ShipmentScore(driver.key, shipment.key,score, debugInfo);
    }
}