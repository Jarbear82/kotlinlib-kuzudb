package com.tau.kuzudb

import com.tau.kuzudb.value.KuzuValue
import kotlin.io.Closeable

/**
 * Represents a connection to a KuzuDatabase. [cite_start]Connections are thread-safe. [cite: 131, 171]
 * [cite_start]Implements [Closeable] for resource management. [cite: 132]
 */
expect class KuzuConnection(database: KuzuDatabase) : Closeable {
    /**
     * [cite_start]Executes a Cypher query. [cite: 133]
     * @param query The Cypher query string.
     * [cite_start]@return A KuzuQueryResult containing the results. [cite: 133]
     */
    suspend fun query(query: String): KuzuQueryResult

    /**
     * [cite_start]Creates a prepared statement for a given Cypher query. [cite: 135]
     * @param query The parameterized Cypher query string.
     * [cite_start]@return A KuzuPreparedStatement that can be executed later. [cite: 135]
     */
    fun prepare(query: String): KuzuPreparedStatement

    /**
     * [cite_start]Executes a prepared statement with bound parameters. [cite: 137]
     * @param preparedStatement The statement to execute.
     * [cite_start]@param params A map of parameter names to their values. [cite: 137]
     * [cite_start]@return A KuzuQueryResult containing the results. [cite: 138]
     */
    suspend fun execute(preparedStatement: KuzuPreparedStatement, params: Map<String, KuzuValue>): KuzuQueryResult

    /**
     * [cite_start]Releases all native resources associated with the connection. [cite: 139]
     */
    override fun close()
}