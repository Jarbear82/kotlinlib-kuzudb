package com.tau.kuzudb

import com.kuzudb.FlatTuple
import com.kuzudb.Value

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

    override fun next(): KuzuTuple {
        if (!hasNext()) throw NoSuchElementException()

        val nativeTuple : FlatTuple = nativeResult.getNext()
        val values = (0 until nativeResult.numColumns).map {
            val nativeValue : Value = nativeTuple.getValue(it.toLong())
            // This is a simplified conversion. A real implementation would need a
            // comprehensive mapping from native `com.kuzudb.Value` to `KuzuValue`
            // and MUST clone the value for safety.
            com.tau.kuzudb.value.KuzuValue.STRING(nativeValue.toString())
        }
        return KuzuTuple(values)
    }
}