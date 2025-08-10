package com.tau.kuzudb

import com.kuzudb.Value

actual class KuzuValue : AutoCloseable {
    internal val nativeValue : Value

    actual fun chekNotDestroyed() {
        try {
            return nativeValue.checkNotDestroyed()
        } catch (e: RuntimeException) {
            throw KuzuException(e.message ?: "Unknown Kuzu Value Exception")
        }
    }

    actual fun clone() {

    }

    actual override fun close() {
        try {
            nativeValue.close()
        } catch (e: RuntimeException) {
            throw KuzuException(e.message ?: "Unknown Kuzu Value Error")
        }
    }


}