package com.tau.kuzudb

import com.kuzudb.FlatTuple

actual class KuzuTuple : AutoCloseable {
    internal val nativeTuple : FlatTuple

    internal constructor(nativeTuple: FlatTuple) {
        this.nativeTuple = nativeTuple
    }
    actual override fun close() {
        nativeTuple.close()
    }

    actual fun getValue(index: Long) {
        KuzuValue(nativeTuple.getValue(index))
    }

    actual override fun toString() : String {
        return nativeTuple.toString()
    }
}