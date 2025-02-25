package com.platformscience.smartroute.data

/**
 * Data object representing a shipment address
 */
data class Shipment(val address: String) {

    /**
     * Unique key for the shipment].
     * Currently, the address is used
     */
    val key: String get() {
        return address;
    }
}
