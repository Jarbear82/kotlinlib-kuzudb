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

    actual fun isSuccess() : Boolean {
        return nativeStatement.isSuccess
    }

    actual fun getErrorMessage() : String {
        try {
           return  nativeStatement.errorMessage
        } catch (e: RuntimeException) {
            throw KuzuException(e.message ?: "Unkown Prepared Statement Exception")
        }
    }
}