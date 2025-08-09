package com.tau.kuzudb

import com.tau.kuzudb.value.KuzuValue


/**
 * Represents a connection to a KuzuDatabase. Connections are thread-safe.
 * Implements AutoCloseable for resource management.
 */
expect class KuzuConnection(database: KuzuDatabase) : AutoCloseable {

    /**
     * Releases all native resources associated with the connection.
     */
    override fun close()

    /**
     * @return The maximum number of threads used for execution in the current connection.
     * @throws TODO: Finish doc comment
     */
    fun getMaxNumThreadForExec() : Long

    /**
     * TODO: Add documentation for setMaxNumThreadForExec()
     */
    fun setMaxNumThreadForExec(numThreads: Long)

    /**
     * Executes a Cypher query.
     * @param query The Cypher query string.
     * @return A KuzuQueryResult containing the results.
     */
    suspend fun query(queryStr: String): KuzuQueryResult

    /**
     * Creates a prepared statement for a given Cypher query.
     * @param query The parameterized Cypher query string.
     * @return A KuzuPreparedStatement that can be executed later.
     */
    fun prepare(queryStr: String): KuzuPreparedStatement

    /**
     * Executes a prepared statement with bound parameters.
     * @param preparedStatement The statement to execute.
     * @param params A map of parameter names to their values.
     * @return A KuzuQueryResult containing the results.
     */
    suspend fun execute(preparedStatement: KuzuPreparedStatement, params: Map<String, KuzuValue>): KuzuQueryResult

    fun interrupt()

    fun setQueryTimeout(timeoutInMs: Long)

}