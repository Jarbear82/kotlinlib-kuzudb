package com.tau.kuzudb

import com.tau.kuzudb.value.KuzuValue

import convertToNative

/**
 * JVM/Android implementation of KuzuPreparedStatement.
 */
actual class KuzuPreparedStatement(
    internal val nativeStatement: com.kuzudb.PreparedStatement
) : AutoCloseable {
    actual fun bind(name: String, value: KuzuValue) {
        val nativeValue = convertToNative(value)
        nativeStatement.
    }

    actual override fun close() {
        nativeStatement.close()
    }
}

