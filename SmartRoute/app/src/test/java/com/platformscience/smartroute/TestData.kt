package com.platformscience.smartroute

import com.platformscience.smartroute.util.checkYForVowel


object BssTestData {
    val KEY_DRIVER_VOWELS="driver.vowels";
    val KEY_DRIVER_CONSONANTS="driver.consonants";
    val KEY_DEST_LENGTH="dest.length";
    val KEY_DEST_LENGTHEVEN="dest.lengthEven";


    val driverTestData: LinkedHashMap<String, HashMap<String, String>> =
        LinkedHashMap<String, HashMap<String, String>>();
    val destinationTestData: LinkedHashMap<String, HashMap<String, String>> =
        LinkedHashMap<String, HashMap<String, String>>();


    init {

        driverTestData.put(
            "Everado Welch",
            linkedMapOf(
                KEY_DRIVER_VOWELS to "5",
                KEY_DRIVER_CONSONANTS to "7"
            )
        );

        // The 'y' in "Mayert" should be considered a vowel, but outside the scope currently
        driverTestData.put(
            "Orval Mayert",
            linkedMapOf(
                KEY_DRIVER_VOWELS to "4",
                KEY_DRIVER_CONSONANTS to "7"
            )
        );

        driverTestData.put(
            "Howard Emmerich",
            linkedMapOf(
                KEY_DRIVER_VOWELS to "5",
                KEY_DRIVER_CONSONANTS to "9"
            )
        );
        driverTestData.put(
            "Izaiah Lowe",
            linkedMapOf(
                KEY_DRIVER_VOWELS to "6",
                KEY_DRIVER_CONSONANTS to "4"
            )
        );
        driverTestData.put(
            "Monica Hermann",
            linkedMapOf(
                KEY_DRIVER_VOWELS to "5",
                KEY_DRIVER_CONSONANTS to "8"
            )
        );
        driverTestData.put(
            "Ellis Wisozk",
            linkedMapOf(
                KEY_DRIVER_VOWELS to "4",
                KEY_DRIVER_CONSONANTS to "7"
            )
        );
        //Trailing 'y' in Murphy at the end considered a vowel if checkYForVowel = true;
        driverTestData.put(
            "Noemie Murphy",
            linkedMapOf(
                KEY_DRIVER_VOWELS to  if (checkYForVowel) "6" else "5",
                KEY_DRIVER_CONSONANTS to if (checkYForVowel) "6" else "7"
            )
        );
        driverTestData.put(
            "Cleve Durgan",
            linkedMapOf(
                KEY_DRIVER_VOWELS to "4",
                KEY_DRIVER_CONSONANTS to "7"
            )
        );

        //Trailing 'y' in first word Murphy is considered a vowel
        //if checkYForVowel = true;
        driverTestData.put(
            "Murphy Mosciski",
            linkedMapOf(
                KEY_DRIVER_VOWELS to if(checkYForVowel) "5" else "4",
                KEY_DRIVER_CONSONANTS to if (checkYForVowel) "9" else "10"
            )
        );

        driverTestData.put(
            "Maiser Sose",
            linkedMapOf(
                KEY_DRIVER_VOWELS to "5",
                KEY_DRIVER_CONSONANTS to "5"
            )
        );




        destinationTestData.put(
            "215 Osinski Manors",
            hashMapOf(
                KEY_DEST_LENGTH to "18",
                KEY_DEST_LENGTHEVEN to "true"
            )
        );
        destinationTestData.put(
            "9856 Marvin Stravenue",
            hashMapOf(
                KEY_DEST_LENGTH to "21",
                KEY_DEST_LENGTHEVEN to "false"
            )
        );
        destinationTestData.put(
            "7127 Kathlyn Ferry",
            hashMapOf(
                KEY_DEST_LENGTH to "18",
                KEY_DEST_LENGTHEVEN to "true"
            )
        );
        destinationTestData.put(
            "987 Champlin Lake",
            hashMapOf(
                KEY_DEST_LENGTH to "17",
                KEY_DEST_LENGTHEVEN to "false"
            )
        );

        destinationTestData.put(
            "63187 Volkman Garden Suite",
            hashMapOf(
                KEY_DEST_LENGTH to "26",
                KEY_DEST_LENGTHEVEN to "true"
            )
        );
        destinationTestData.put(
            "75855 Dessie Lights",
            hashMapOf(
                KEY_DEST_LENGTH to "19",
                KEY_DEST_LENGTHEVEN to "false"
            )
        );
        destinationTestData.put(
            "1797 Adolf Island Apt. 744",
            hashMapOf(
                KEY_DEST_LENGTH to "26",
                KEY_DEST_LENGTHEVEN to "true"
            )
        );
        destinationTestData.put(
            "2431 Lindren Corners",
            hashMapOf(
                KEY_DEST_LENGTH to "20",
                KEY_DEST_LENGTHEVEN to "true"
            )
        );
        destinationTestData.put(
            "8725 Aufderhar River Suite 859",
            hashMapOf(
                KEY_DEST_LENGTH to "30",
                KEY_DEST_LENGTHEVEN to "true"
            )
        );
        destinationTestData.put(
            "79035 Shanna Light Apt. 322",
            hashMapOf(
                KEY_DEST_LENGTH to "27",
                KEY_DEST_LENGTHEVEN to "false"
            )
        );

    }


}

