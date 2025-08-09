package com.tau.kuzudb

import convertToNative

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

    actual suspend fun execute(preparedStatement: KuzuPreparedStatement): KuzuQueryResult {
        return withContext(Dispatchers.IO) {
            try {
                // The user is now responsible for calling bind() on the preparedStatement first.
                val nativeResult = nativeConnection.execute(preparedStatement.nativeStatement)
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