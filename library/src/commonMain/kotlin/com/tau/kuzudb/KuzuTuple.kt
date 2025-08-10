package com.tau.kuzudb

import com.tau.kuzudb.value.KuzuValue

/**
 * Represents a single row in a KuzuQueryResult.
 *
 * @param values The list of values in the tuple.
 */
expect class KuzuTuple() : AutoCloseable {

    override fun close()

    fun getValue(index: Long)

    override fun toString() : String
}