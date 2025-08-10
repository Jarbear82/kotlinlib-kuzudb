package com.tau.kuzudb

import com.kuzudb.FlatTuple

actual class KuzuTuple : AutoCloseable {
    internal val nativeTuple : FlatTuple = FlatTuple()

    actual override fun close() {
        nativeTuple.close()
    }

    actual fun getValue(index: Long) KuzuValue {
        try {
            return KuzuValue(nativeTuple.getValue(index))
        } catch (e: RuntimeException) {
            throw KuzuException(message = e.message ?: "Unknown KuzuTuple Exception")
        } catch (e: RuntimeException) {
            throw KuzuException(e.message ?: "")
        }
    }

    actual override fun toString() : String {
        return nativeTuple.toString()
    }
}