package com.tau.kuzudb

import com.kuzudb.KuzuMap as NativeKuzuMap

actual class KuzuMap : AutoCloseable {
    internal val nativeMap: NativeKuzuMap
    actual constructor(value: KuzuValue) {
        this.nativeMap = NativeKuzuMap(value.nativeValue)
    }

    actual constructor(keys: Array<KuzuValue>, values: Array<KuzuValue>) {
        this.nativeMap = NativeKuzuMap(keys.map{it.nativeValue}.toTypedArray(), values.map{it.nativeValue}.toTypedArray())
    }

    internal constructor(nativeMap: NativeKuzuMap) {
        this.nativeMap = nativeMap
    }

    actual override fun close() {
        nativeMap.close()
    }

    actual fun getKey(index: Long): KuzuValue {
        return KuzuValue(nativeMap.getKey(index))
    }

    actual fun getNumFields(): Long {
        return nativeMap.numFields
    }

    actual fun getValue(): KuzuValue {
        return KuzuValue(nativeMap.value)
    }

    actual fun getValue(index: Long): KuzuValue {
        return KuzuValue(nativeMap.getValue(index))
    }
}