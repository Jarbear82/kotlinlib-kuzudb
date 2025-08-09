package com.tau.kuzudb

import com.tau.kuzudb.value.KuzuValue

/**
 * Represents a prepared statement for a given Cypher query.
 * Implements [AutoCloseable] for resource management.
 */
expect class KuzuPreparedStatement : AutoCloseable {
    /**
     * Binds a value to a parameter in the prepared statement.
     *
     * @param name The name of the parameter.
     * @param value The value to bind.
     */
    fun bind(name: String, value: KuzuValue)

    /**
     * Releases all native resources associated with the prepared statement.
     */
    override fun close()
}