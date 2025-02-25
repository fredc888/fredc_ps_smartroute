package com.platformscience.smartroute.data

/**
 * Data object representing a driver.
 * For future expansion, this object will contain additional details about the driver
 */
data class Driver(val name: String) {

    /**
     * Unique key for the driver.
     * Currently, the name is used
     */
    val key: String get(){
        return name;
    }
}
