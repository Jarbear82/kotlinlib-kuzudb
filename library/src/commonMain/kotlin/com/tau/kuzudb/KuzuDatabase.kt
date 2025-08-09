package com.tau.kuzudb


/**
 * Represents a KuzuDB database instance, either on-disk or in-memory.
 * Implements Closeable for resource management.
 */
expect class KuzuDatabase : AutoCloseable {
    /**
     * Creates or opens a database.
     * @param path The path to the database file. Use an empty string or ":memory:" for an in-memory database.
     * @param config Optional advanced configuration settings.
     */
    constructor(path: String, config: KuzuDatabaseConfig? = null)

    /**
     * Gets the version of the Kuzu library.
     */
    fun getVersion(): String

    /**
     * Releases all native resources associated with the database.
     */
    override fun close()
}