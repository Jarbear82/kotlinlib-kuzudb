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

        val nativeTuple: com.kuzudb.FlatTuple = nativeResult.getNext()
        val values = (0 until nativeResult.numColumns).map { i ->
            val nativeValue: com.kuzudb.Value = nativeTuple.getValue(i.toLong())
            // It's crucial to clone the value for safety, as the underlying memory can be freed.
            val clonedValue = nativeValue.clone()
            convertFromNative(clonedValue)
        }
        return KuzuTuple(values)
    }

    private fun convertFromNative(nativeValue: com.kuzudb.Value): KuzuValue {
        if (nativeValue.isNull) return KuzuValue.NULL

        return when (val value = nativeValue.value) {
            is Boolean -> KuzuValue.BOOL(value)
            is Byte -> KuzuValue.INT8(value)
            is Short -> KuzuValue.INT16(value)
            is Int -> KuzuValue.INT32(value)
            is Long -> KuzuValue.INT64(value)
            is Float -> KuzuValue.FLOAT(value)
            is Double -> KuzuValue.DOUBLE(value)
            is String -> KuzuValue.STRING(value)
            is ByteArray -> KuzuValue.BLOB(value)
            is List<*> -> KuzuValue.LIST(value.map { convertFromNative(it as com.kuzudb.Value) })
            is Map<*, *> -> KuzuValue.MAP(
                (value as Map<String, com.kuzudb.Value>).mapValues { convertFromNative(it.value) }
            )
            // Add other conversions as needed...
            else -> throw KuzuTypeException("Unsupported native type: ${value.javaClass.name}")
        }
}