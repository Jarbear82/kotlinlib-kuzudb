package com.tau.kuzudb

/**
 * Represents a prepared statement for a given Cypher query.
 * Implements [AutoCloseable] for resource management.
 */
expect class KuzuPreparedStatement : AutoCloseable {
    /**
     * Releases all native resources associated with the prepared statement.
     */
    override fun close()

    fun isSuccess() : Boolean

    fun getErrorMessage() : String
}