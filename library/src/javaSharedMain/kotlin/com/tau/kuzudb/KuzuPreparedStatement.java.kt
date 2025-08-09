package com.tau.kuzudb

import com.tau.kuzudb.value.KuzuValue

/**
 * JVM/Android implementation of KuzuPreparedStatement.
 */
actual class KuzuPreparedStatement(
    internal val nativeStatement: com.kuzudb.PreparedStatement
) : AutoCloseable {
    actual fun bind(name: String, value: KuzuValue) {
        val nativeValue = convertToNative(value)
        nativeStatement.bind(name, nativeValue)
    }

    private fun convertToNative(kuzuValue: KuzuValue): com.kuzudb.Value {
        return when (kuzuValue) {
            is KuzuValue.BOOL -> com.kuzudb.Value(kuzuValue.nativeValue)
            is KuzuValue.INT8 -> com.kuzudb.Value(kuzuValue.nativeValue)
            is KuzuValue.INT16 -> com.kuzudb.Value(kuzuValue.nativeValue)
            is KuzuValue.INT32 -> com.kuzudb.Value(kuzuValue.nativeValue)
            is KuzuValue.INT64 -> com.kuzudb.Value(kuzuValue.nativeValue)
            is KuzuValue.FLOAT -> com.kuzudb.Value(kuzuValue.nativeValue)
            is KuzuValue.DOUBLE -> com.kuzudb.Value(kuzuValue.nativeValue)
            is KuzuValue.STRING -> com.kuzudb.Value(kuzuValue.nativeValue)
            is KuzuValue.BLOB -> com.kuzudb.Value(kuzuValue.nativeValue)
            is KuzuValue.LIST -> com.kuzudb.Value(kuzuValue.nativeValue.map { convertToNative(it) })
            is KuzuValue.MAP -> com.kuzudb.Value(kuzuValue.nativeValue.mapValues { convertToNative(it.value) })
            is KuzuValue.NULL -> com.kuzudb.Value(null)
            // Add other conversions as needed...
            else -> throw KuzuTypeException("Unsupported KuzuValue type: ${kuzuValue::class.simpleName}")
        }

    actual override fun close() {
        nativeStatement.close()
    }
}