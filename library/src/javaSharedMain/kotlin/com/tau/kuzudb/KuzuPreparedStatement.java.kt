package com.tau.kuzudb

import com.tau.kuzudb.value.KuzuValue

/**
 * JVM/Android implementation of KuzuPreparedStatement.
 */
actual class KuzuPreparedStatement(
    internal val nativeStatement: com.kuzudb.PreparedStatement
) : AutoCloseable {

    actual override fun close() {
        nativeStatement.close()
    }
}