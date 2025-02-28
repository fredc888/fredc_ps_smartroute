package com.platformscience.smartroute

import com.platformscience.smartroute.data.Shipment
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.util.checkYForVowel


object BssTestData {
    val KEY_DRIVER_VOWELS="driver.vowels";
    val KEY_DRIVER_CONSONANTS="driver.consonants";
    val KEY_SHIPMENT_LENGTH="shipment.length";
    val KEY_SHIPMENT_LENGTHEVEN="shipment.lengthEven";
    val KEY_GCF = "gcf";

    val driverTestData: LinkedHashMap<String, HashMap<String, String>> =
        LinkedHashMap();

    val shipmentTestData: LinkedHashMap<String, HashMap<String, String>> =
        LinkedHashMap();

    val driverDestMappedData: LinkedHashMap<String, HashMap<String, String>> =
        LinkedHashMap();

    val testDrivers:ArrayList<Driver> = ArrayList();
    val testShipments:ArrayList<Shipment> = ArrayList();

    init {
        createDrivers();
        createDestinations();
        createDriverDestinationMappedData();
        initDataObjects();
    }

    private fun createDrivers() {
        driverTestData.put(
            "Everado Welch",
            linkedMapOf(KEY_DRIVER_VOWELS to "5", KEY_DRIVER_CONSONANTS to "7")
        );

        // The 'y' in "Mayert" should be considered a vowel, but outside the scope currently
        driverTestData.put(
            "Orval Mayert",
            linkedMapOf(KEY_DRIVER_VOWELS to "4", KEY_DRIVER_CONSONANTS to "7")
        );

        driverTestData.put(
            "Howard Emmerich",
            linkedMapOf(KEY_DRIVER_VOWELS to "5", KEY_DRIVER_CONSONANTS to "9")
        );
        driverTestData.put(
            "Izaiah Lowe",
            linkedMapOf(KEY_DRIVER_VOWELS to "6", KEY_DRIVER_CONSONANTS to "4")
        );
        driverTestData.put(
            "Monica Hermann",
            linkedMapOf(KEY_DRIVER_VOWELS to "5", KEY_DRIVER_CONSONANTS to "8")
        );
        driverTestData.put(
            "Ellis Wisozk",
            linkedMapOf(KEY_DRIVER_VOWELS to "4", KEY_DRIVER_CONSONANTS to "7")
        );

        //Trailing 'y' in Murphy at the end considered a vowel if checkYForVowel = true;
        driverTestData.put(
            "Noemie Murphy",
            linkedMapOf(
                KEY_DRIVER_VOWELS to if (checkYForVowel) "6" else "5",
                KEY_DRIVER_CONSONANTS to if (checkYForVowel) "6" else "7"
            )
        );

        driverTestData.put(
            "Cleve Durgan",
            linkedMapOf(KEY_DRIVER_VOWELS to "4", KEY_DRIVER_CONSONANTS to "7")
        );

        //Trailing 'y' in first word Murphy is considered a vowel
        //if checkYForVowel = true;
        driverTestData.put(
            "Murphy Mosciski",
            linkedMapOf(
                KEY_DRIVER_VOWELS to if (checkYForVowel) "5" else "4",
                KEY_DRIVER_CONSONANTS to if (checkYForVowel) "9" else "10"
            )
        );

        driverTestData.put(
            "Maiser Sose",
            linkedMapOf(KEY_DRIVER_VOWELS to "5", KEY_DRIVER_CONSONANTS to "5")
        );


    }


    private fun createDestinations() {
        shipmentTestData.put(
            "215 Osinski Manors",
            hashMapOf(
                KEY_SHIPMENT_LENGTH to "18",
                KEY_SHIPMENT_LENGTHEVEN to "true"
            )
        );
        shipmentTestData.put(
            "9856 Marvin Stravenue",
            hashMapOf(
                KEY_SHIPMENT_LENGTH to "21",
                KEY_SHIPMENT_LENGTHEVEN to "false"
            )
        );
        shipmentTestData.put(
            "7127 Kathlyn Ferry",
            hashMapOf(
                KEY_SHIPMENT_LENGTH to "18",
                KEY_SHIPMENT_LENGTHEVEN to "true"
            )
        );
        shipmentTestData.put(
            "987 Champlin Lake",
            hashMapOf(
                KEY_SHIPMENT_LENGTH to "17",
                KEY_SHIPMENT_LENGTHEVEN to "false"
            )
        );

        shipmentTestData.put(
            "63187 Volkman Garden Suite",
            hashMapOf(
                KEY_SHIPMENT_LENGTH to "26",
                KEY_SHIPMENT_LENGTHEVEN to "true"
            )
        );
        shipmentTestData.put(
            "75855 Dessie Lights",
            hashMapOf(
                KEY_SHIPMENT_LENGTH to "19",
                KEY_SHIPMENT_LENGTHEVEN to "false"
            )
        );
        shipmentTestData.put(
            "1797 Adolf Island Apt. 744",
            hashMapOf(
                KEY_SHIPMENT_LENGTH to "26",
                KEY_SHIPMENT_LENGTHEVEN to "true"
            )
        );
        shipmentTestData.put(
            "2431 Lindren Corners",
            hashMapOf(
                KEY_SHIPMENT_LENGTH to "20",
                KEY_SHIPMENT_LENGTHEVEN to "true"
            )
        );
        shipmentTestData.put(
            "8725 Aufderhar River Suite 859",
            hashMapOf(
                KEY_SHIPMENT_LENGTH to "30",
                KEY_SHIPMENT_LENGTHEVEN to "true"
            )
        );
        shipmentTestData.put(
            "79035 Shanna Light Apt. 322",
            hashMapOf(
                KEY_SHIPMENT_LENGTH to "27",
                KEY_SHIPMENT_LENGTHEVEN to "false"
            )
        );
    }

    private fun createDriverDestinationMappedData() {
        val driverKeys = driverTestData.keys;
        val shipmentKeys = shipmentTestData.keys;

        addMappedData(driverKeys.elementAt(0),shipmentKeys.elementAt(0), KEY_GCF, "1")
        addMappedData(driverKeys.elementAt(0),shipmentKeys.elementAt(1), KEY_GCF, "1")
        addMappedData(driverKeys.elementAt(0),shipmentKeys.elementAt(2), KEY_GCF, "1")
        addMappedData(driverKeys.elementAt(0),shipmentKeys.elementAt(3), KEY_GCF, "1")
        addMappedData(driverKeys.elementAt(0),shipmentKeys.elementAt(4), KEY_GCF, "13")
        addMappedData(driverKeys.elementAt(0),shipmentKeys.elementAt(5), KEY_GCF, "1")
        addMappedData(driverKeys.elementAt(0),shipmentKeys.elementAt(6), KEY_GCF, "13")
        addMappedData(driverKeys.elementAt(0),shipmentKeys.elementAt(7), KEY_GCF, "1")
        addMappedData(driverKeys.elementAt(0),shipmentKeys.elementAt(8), KEY_GCF, "1")
        addMappedData(driverKeys.elementAt(0),shipmentKeys.elementAt(9), KEY_GCF, "1")
        addMappedData(driverKeys.elementAt(1),shipmentKeys.elementAt(0), KEY_GCF, "6")
        addMappedData(driverKeys.elementAt(1),shipmentKeys.elementAt(1), KEY_GCF, "3")
        addMappedData(driverKeys.elementAt(1),shipmentKeys.elementAt(2), KEY_GCF, "6")
        addMappedData(driverKeys.elementAt(1),shipmentKeys.elementAt(3), KEY_GCF, "1")
        addMappedData(driverKeys.elementAt(1),shipmentKeys.elementAt(4), KEY_GCF, "2")
        addMappedData(driverKeys.elementAt(1),shipmentKeys.elementAt(5), KEY_GCF, "1")
        addMappedData(driverKeys.elementAt(1),shipmentKeys.elementAt(6), KEY_GCF, "2")
        addMappedData(driverKeys.elementAt(1),shipmentKeys.elementAt(7), KEY_GCF, "4")
        addMappedData(driverKeys.elementAt(1),shipmentKeys.elementAt(8), KEY_GCF, "6")
        addMappedData(driverKeys.elementAt(1),shipmentKeys.elementAt(9), KEY_GCF, "3")
        addMappedData(driverKeys.elementAt(2),shipmentKeys.elementAt(0), KEY_GCF, "3")
        addMappedData(driverKeys.elementAt(2),shipmentKeys.elementAt(1), KEY_GCF, "3")
        addMappedData(driverKeys.elementAt(2),shipmentKeys.elementAt(2), KEY_GCF, "3")
        addMappedData(driverKeys.elementAt(2),shipmentKeys.elementAt(3), KEY_GCF, "1")




        /*
        addMappedData(driverKeys.elementAt(2),shipmentKeys.elementAt(4), KEY_GCF, "3")
        addMappedData(driverKeys.elementAt(2),shipmentKeys.elementAt(5), KEY_GCF, "3")
        addMappedData(driverKeys.elementAt(2),shipmentKeys.elementAt(6), KEY_GCF, "3")
        addMappedData(driverKeys.elementAt(2),shipmentKeys.elementAt(7), KEY_GCF, "3")
        addMappedData(driverKeys.elementAt(2),shipmentKeys.elementAt(8), KEY_GCF, "3")
        addMappedData(driverKeys.elementAt(2),shipmentKeys.elementAt(9), KEY_GCF, "3")
*/
    }

    private fun initDataObjects() {
        driverTestData.keys.forEach{ driverName ->
            testDrivers.add(Driver(driverName));
        }

        shipmentTestData.keys.forEach{ shipmentAddress ->
            testShipments.add(Shipment(shipmentAddress));
        }
    }

    private fun addMappedData(driverKey: String, shipmentKey:String,
                              propKey:String, propValue:String){

        val mapKey = driverKey + ":"+shipmentKey;
        var mappedData = driverDestMappedData.get(mapKey)
        if (mappedData.isNullOrEmpty()){
            mappedData = HashMap<String,String>()
            driverDestMappedData.put(mapKey, mappedData);
        };
        mappedData!!.put(propKey,propValue);
    }



    fun getMappedData(driverKey: String, shipmentKey:String, propKey:String) : String? {
        val mapKey = driverKey + ":"+shipmentKey;
        return driverDestMappedData.get(mapKey)?.get(propKey);
    }

}

