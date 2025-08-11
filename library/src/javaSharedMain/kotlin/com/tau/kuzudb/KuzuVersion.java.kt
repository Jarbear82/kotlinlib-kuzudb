package com.tau.kuzudb

import com.kuzudb.Version as NativeVersion

actual object KuzuVersion {
    /**
     * Get the storage version of the Kuzu.
     */
    actual fun getStorageVersion(): Long = NativeVersion.getStorageVersion()

    /**
     * Get the version of the Kuzu.
     */
    actual fun getVersion(): String = NativeVersion.getVersion()
}