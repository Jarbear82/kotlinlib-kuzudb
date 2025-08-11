package com.tau.kuzudb

import com.tau.kuzudb.KuzuValue

/**
 * Represents a connection to a KuzuDatabase. Connections are thread-safe.
 * Multiple connections can connect to the same Database instance in a multi-threaded environment.
 * Implements AutoCloseable for resource management.
 *
 * @param database The KuzuDatabase instance to connect to.
 */
expect class KuzuConnection(database: KuzuDatabase) : AutoCloseable {

    /**
     * Releases all native resources associated with the connection.
     */
    override fun close()

    /**
     * Returns the maximum number of threads used for execution in the current connection.
     * @return The maximum number of threads for execution.
     * @throws KuzuConnectionException If the connection has been destroyed.
     */
    fun getMaxNumThreadForExec() : Long

    /**
     * Sets the maximum number of threads to use for execution in the current connection.
     * @param numThreads The maximum number of threads to use.
     * @throws KuzuConnectionException If the connection has been destroyed.
     */
    fun setMaxNumThreadForExec(numThreads: Long)

    /**
     * Executes a Cypher query and returns the result.
     * @param queryStr The Cypher query string to execute.
     * @return A [KuzuQueryResult] containing the results.
     * @throws KuzuQueryException If the query execution fails.
     * @throws KuzuConnectionException If the connection has been destroyed.
     */
    suspend fun query(queryStr: String): KuzuQueryResult

    /**
     * Prepares a given query and returns the prepared statement.
     * @param queryStr The parameterized Cypher query string.
     * @return A [KuzuPreparedStatement] that can be executed later.
     * @throws KuzuConnectionException If the connection has been destroyed.
     */
    fun prepare(queryStr: String): KuzuPreparedStatement

    /**
     * Executes the given prepared statement with args and returns the result.
     * @param preparedStatement The statement to execute.
     * @param params A map of parameter names to their values.
     * @return A [KuzuQueryResult] containing the results.
     * @throws KuzuQueryException If the execution fails.
     * @throws KuzuConnectionException If the connection has been destroyed.
     */
    suspend fun execute(preparedStatement: KuzuPreparedStatement, params: Map<String, KuzuValue>): KuzuQueryResult

    /**
     * Interrupts all queries currently executed within this connection.
     * @throws KuzuConnectionException If the connection has been destroyed.
     */
    fun interrupt()

    /**
     * Sets the query timeout value of the current connection. A value of zero (the default) disables the timeout.
     * @param timeoutInMs The query timeout value in milliseconds.
     * @throws KuzuConnectionException If the connection has been destroyed.
     */
    fun setQueryTimeout(timeoutInMs: Long)

}