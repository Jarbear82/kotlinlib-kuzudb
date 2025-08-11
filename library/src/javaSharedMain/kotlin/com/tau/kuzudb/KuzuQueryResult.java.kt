package com.tau.kuzudb

/**
 * JVM/Android implementation of KuzuQueryResult.
 */
actual class KuzuQueryResult(
    internal val nativeResult: com.kuzudb.QueryResult
) : AutoCloseable, Iterator<KuzuTuple> {

    actual override fun close() = nativeResult.close()

    actual fun getColumnByDataType(index: Long) {
        nativeResult.getColumnDataType(index)
    }

    actual fun getColumnName(index: Long): String = nativeResult.getColumnName(index)

    actual fun getErrorMessage(): String = nativeResult.errorMessage

    actual fun getNext(): KuzuTuple = KuzuTuple(nativeResult.next)

    actual fun getNextQueryResult(): KuzuQueryResult = KuzuQueryResult(nativeResult.nextQueryResult)

    actual fun getNumColumns(): Long = nativeResult.numColumns

    actual fun getNumTuples(): Long = nativeResult.numTuples

    actual fun getQuerySummary(): KuzuQuerySummary = KuzuQuerySummary(nativeResult.querySummary)

    actual override fun hasNext(): Boolean = nativeResult.hasNext()

    actual fun hasNextQueryResult(): Boolean = nativeResult.hasNextQueryResult()

    actual fun isOwnedByCPP(): Boolean = nativeResult.isOwnedByCPP

    actual fun isSuccess(): Boolean = nativeResult.isSuccess

    actual fun resetIterator() = nativeResult.resetIterator()

    actual override fun toString(): String = nativeResult.toString()

    override fun next(): KuzuTuple {
        return getNext()
    }
}