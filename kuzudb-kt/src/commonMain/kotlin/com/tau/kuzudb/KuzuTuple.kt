package com.tau.kuzudb

/**
 * Represents a single row in a KuzuQueryResult.
 *
 * @param values The list of values in the tuple.
 */
expect class KuzuTuple() : AutoCloseable {

    override fun close()

    fun getValue(index: Long): KuzuValue

    override fun toString() : String
}