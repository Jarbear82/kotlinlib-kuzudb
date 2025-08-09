package com.tau.kuzudb

import com.tau.kuzudb.KuzuException

import com.tau.kuzudb.value.KuzuValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * JVM/Android implementation of KuzuConnection.
 */
actual class KuzuConnection actual constructor(database: KuzuDatabase) : AutoCloseable {
    internal val nativeConnection = com.kuzudb.Connection(database.nativeDatabase)

    actual suspend fun query(query: String): KuzuQueryResult {
        return withContext(Dispatchers.IO) {
            try {
                val nativeResult = nativeConnection.query(query)
                KuzuQueryResult(nativeResult)
            } catch (e: KuzuException) {
                throw KuzuQueryException(e.message ?: "Unknown Kuzu query error")
            }
        }
    }

    actual fun prepare(query: String): KuzuPreparedStatement {
        return KuzuPreparedStatement(nativeConnection.prepare(query))
    }

    actual suspend fun execute(
        preparedStatement: KuzuPreparedStatement,
        params: Map<String, KuzuValue>
    ): KuzuQueryResult {
        return withContext(Dispatchers.IO) {
            try {
                // The Java API doesn't have a direct execute with map, so we bind manually
                val nativeStatement = preparedStatement.nativeStatement

                // Create a map to hold the native parameter values
                val nativeParams = mutableMapOf<String, com.kuzudb.Value>()

                // Convert the KuzuValue objects to their native counterparts
                params.forEach { (key, value) ->
                    nativeParams[key] = value.nativeValue
                }

                // Execute the prepared statement with the native parameters
                val nativeResult = nativeConnection.execute(nativeStatement, nativeParams)
                KuzuQueryResult(nativeResult)
            } catch (e: KuzuException) {
                throw KuzuQueryException(e.message ?: "Unknown Kuzu query error")
            }
        }
    }

    actual override fun close() {
        nativeConnection.close()
    }
}