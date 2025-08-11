package com.tau.kuzudb

/**
 * Represents the result of a query execution.
 * Acts as an iterator over the result tuples and implements [AutoCloseable].
 */
expect class KuzuQueryResult : AutoCloseable, Iterator<KuzuTuple> {
    /**
     * Close the query result and release the underlying resources.
     */
    override fun close()

    /**
     * Get the column data type at the given index.
     */
    fun getColumnDataType(index: Long): KuzuDataType

    /**
     * Get the column name at the given index.
     */
    fun getColumnName(index: Long) : String

    /**
     * Get the error message if any.
     */
    fun getErrorMessage() : String

    /**
     * Get the next tuple
     */
    fun getNext() : KuzuTuple

    /**
     * Get the next Query Result
     */
    fun getNextQueryResult() : KuzuQueryResult

    /**
     * Get the number of columns in the query result
     */
    fun getNumColumns() : Long

    /**
     * Get the number of tuples in the query result
     */
    fun getNumTuples() : Long

    /**
     * Get the query summary
     */
    fun getQuerySummary() : KuzuQuerySummary

    /**
     * Return if the query result has next tuple or not.
     */
    override fun hasNext() : Boolean

    /**
     * Return if the query result has next query result or not.
     */
    fun hasNextQueryResult() : Boolean

    fun isOwnedByCPP() : Boolean

    /**
     * Check if the query is executed successfully.
     */
    fun isSuccess() : Boolean

    /**
     * Reset the query result iterator.
     */
    fun resetIterator()

    /**
     * Convert the query result to string.
     */
    override fun toString(): String


}