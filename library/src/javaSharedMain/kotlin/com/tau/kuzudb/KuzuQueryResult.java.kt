package com.tau.kuzudb

/**
 * JVM/Android implementation of KuzuQueryResult.
 */
actual class KuzuQueryResult(
    internal val nativeResult: com.kuzudb.QueryResult
) : AutoCloseable, Iterator<KuzuTuple> {

    actual fun isSuccess(): Boolean = nativeResult.isSuccess

    actual fun getErrorMessage(): String? = nativeResult.errorMessage

    actual fun getNumColumns(): Long = nativeResult.numColumns

    actual fun getColumnName(index: Long): String = nativeResult.getColumnName(index)

    actual override fun close() = nativeResult.close()

    override fun hasNext(): Boolean = nativeResult.hasNext()

    override fun next(): KuzuTuple = KuzuTuple(nativeResult.next)
    }

}