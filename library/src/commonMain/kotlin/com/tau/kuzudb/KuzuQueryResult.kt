package com.tau.kuzudb


/**
 * Represents the result of a query execution.
 * Acts as an iterator over the result tuples and implements [AutoCloseable].
 */
expect class KuzuQueryResult : AutoCloseable, Iterator<KuzuTuple> {
    /**
     * Checks if the query executed successfully.
     */
    fun isSuccess(): Boolean

    /**
     * Gets the error message if the query failed.
     * @return The error message, or null if the query was successful.
     */
    fun getErrorMessage(): String?

    /**
     * Gets the number of columns in the result set.
     */
    fun getNumColumns(): Long

    /**
     * Gets the name of the column at a given index.
     */
    fun getColumnName(index: Long): String

    /**
     * Releases all native resources associated with the query result.
     */
    override fun close()
}