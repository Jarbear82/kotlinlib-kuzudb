package com.tau.kuzudb

/**
 * Represents a KuzuDB database instance, either on-disk or in-memory.
 * Implements Closeable for resource management.
 */
expect class KuzuDatabase : AutoCloseable {

    /**
     * Default Empty Constructor
     */
    constructor()

    /**
     * Creates or opens a database.
     * @param path The path to the database file. Use an empty string or ":memory:" for an in-memory database.
     */
    constructor(path: String)

    /**
     * Creates or opens a database.
     * @param config Advanced configuration settings.
     */
    // TODO: Get test to pass
    // constructor(config: KuzuDatabaseConfig)

    /**
     * Releases all native resources associated with the database.
     */
    override fun close()
}