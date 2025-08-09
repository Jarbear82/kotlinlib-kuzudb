package com.tau.kuzudb

import com.tau.kuzudb.value.KuzuValue

/**
 * JVM/Android implementation of KuzuPreparedStatement.
 */
actual class KuzuPreparedStatement(
    internal val nativeStatement: com.kuzudb.PreparedStatement
) : AutoCloseable {
    actual fun bind(name: String, value: KuzuValue) {
        // This is a simplified binding. A real implementation would need to
        // convert KuzuValue to the appropriate native `com.kuzudb.Value`
    }

    actual override fun close() {
        nativeStatement.close()
    }
}