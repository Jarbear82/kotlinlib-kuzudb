package com.tau.kuzudb

actual object KuzuVersion {
    /**
     * Get the storage version of the Kuzu.
     */
    fun getStorageVersion() : Long

    /**
     * Get the version of the Kuzu.
     */
    fun getVersion() : String
}