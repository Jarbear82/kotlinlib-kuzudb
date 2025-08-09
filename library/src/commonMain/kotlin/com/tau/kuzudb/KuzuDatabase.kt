package com.tau.kuzudb

import kotlin.io.Closeable

/**
 * [cite_start]Represents a KuzuDB database instance, either on-disk or in-memory. [cite: 126]
 * [cite_start]Implements [Closeable] for resource management. [cite: 127]
 */
expect class KuzuDatabase : Closeable {
    /**
     * Creates or opens a database.
     * @param path The path to the database file. [cite_start]Use an empty string or ":memory:" for an in-memory database. [cite: 128]
     * [cite_start]@param config Optional advanced configuration settings. [cite: 129]
     */
    constructor(path: String, config: KuzuDatabaseConfig? = null)

    /**
     * [cite_start]Gets the version of the Kuzu library. [cite: 129]
     */
    fun getVersion(): String

    /**
     * [cite_start]Releases all native resources associated with the database. [cite: 131]
     */
    override fun close()
}