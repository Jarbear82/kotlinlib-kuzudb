package com.tau.kuzudb

import com.tau.kuzudb.KuzuValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * JVM/Android implementation of KuzuConnection.
 */
actual class KuzuConnection actual constructor(database: KuzuDatabase) : AutoCloseable {
    internal val nativeConnection = com.kuzudb.Connection(database.nativeDatabase)

    actual override fun close() {
        nativeConnection.close()
    }

    actual fun getMaxNumThreadForExec() : Long {
        try {
            return nativeConnection.maxNumThreadForExec
        }
        catch (e: RuntimeException) {
            throw KuzuConnectionException(e.message ?: "Unknown Kuzu Connection Error", e)
        }

    }

    actual fun setMaxNumThreadForExec(numThreads: Long) {
        nativeConnection.maxNumThreadForExec = numThreads
    }

    actual suspend fun query(queryStr: String): KuzuQueryResult {
        return withContext(Dispatchers.IO) {
            try {
                val nativeResult = nativeConnection.query(queryStr)
                KuzuQueryResult(nativeResult)
            } catch (e: KuzuException) {
                throw KuzuQueryException(e.message ?: "Unknown Kuzu query error", e)
            }
        }
    }

    actual fun prepare(queryStr: String): KuzuPreparedStatement {
        return KuzuPreparedStatement(nativeConnection.prepare(queryStr))
    }

    actual suspend fun execute(preparedStatement: KuzuPreparedStatement, params: Map<String, KuzuValue>): KuzuQueryResult {
        return withContext(Dispatchers.IO) {
            try {
                val nativeParams = params.mapValues { it.value.nativeValue }
                val nativeResult = nativeConnection.execute(preparedStatement.nativeStatement, nativeParams)
                KuzuQueryResult(nativeResult)
            } catch (e: KuzuException) {
                throw KuzuQueryException(e.message ?: "Unknown Kuzu query error", e)
            }
        }
    }

    actual fun interrupt() {
        try{
            nativeConnection.interrupt()
        } catch (e: RuntimeException) {
            throw KuzuConnectionException(e.message ?: "Unknown Kuzu Connection Error", e)
        }
    }

    actual fun setQueryTimeout(timeoutInMs: Long) {
        try {
            nativeConnection.setQueryTimeout(timeoutInMs)
        } catch (e: RuntimeException) {
            throw KuzuConnectionException(e.message ?: "Unkown Kuzu Connection Error", e)
        }
    }
}