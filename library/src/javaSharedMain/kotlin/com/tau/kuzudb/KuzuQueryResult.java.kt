package com.tau.kuzudb

import convertFromNative

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

        val nativeTuple: com.kuzudb.FlatTuple = nativeResult.getNext()
        val values = (0 until nativeResult.numColumns).map { i ->
            val nativeValue: com.kuzudb.Value = nativeTuple.getValue(i.toLong())
            // It's crucial to clone the value for safety, as the underlying memory can be freed.
            val clonedValue = nativeValue.clone()
            convertFromNative(clonedValue)
        }
        return KuzuTuple(values)
    }

}