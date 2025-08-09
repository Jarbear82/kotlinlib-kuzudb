package com.tau.kuzudb

import kotlin.io.Closeable

/**
 * [cite_start]Represents the result of a query execution. [cite: 140]
 * [cite_start]Acts as an iterator over the result tuples and implements [Closeable]. [cite: 140]
 */
expect class KuzuQueryResult : Closeable, Iterator<KuzuTuple> {
    /**
     * [cite_start]Checks if the query executed successfully. [cite: 142]
     */
    fun isSuccess(): Boolean

    /**
     * Gets the error message if the query failed.
     * [cite_start]@return The error message, or null if the query was successful. [cite: 143]
     */
    fun getErrorMessage(): String?

    /**
     * [cite_start]Gets the number of columns in the result set. [cite: 145]
     */
    fun getNumColumns(): Long

    /**
     * [cite_start]Gets the name of the column at a given index. [cite: 146]
     */
    fun getColumnName(index: Long): String

    /**
     * [cite_start]Releases all native resources associated with the query result. [cite: 147]
     */
    override fun close()
}