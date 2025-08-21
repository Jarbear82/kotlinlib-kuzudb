package com.tau.kuzudb

import com.kuzudb.KuzuList as NativeKuzuList

actual class KuzuList : AutoCloseable {
    internal val nativeList: NativeKuzuList

    actual constructor(type: KuzuDataType, numElements: Long) {
        this.nativeList = NativeKuzuList(type.nativeDataType, numElements)
    }

    actual constructor(value: KuzuValue) {
        this.nativeList = NativeKuzuList(value.nativeValue)
    }

    actual constructor(values: Array<KuzuValue>) {
        this.nativeList = NativeKuzuList(values.map { it.nativeValue }.toTypedArray())
    }



    internal constructor(nativeList: NativeKuzuList) {
        this.nativeList = nativeList
    }

    actual override fun close() {
        nativeList.close()
    }

    actual fun getListElement(index: Long): KuzuValue {
        return KuzuValue(nativeList.getListElement(index))
    }

    actual fun getListSize(): Long {
        return nativeList.listSize
    }

    actual fun getValue(): KuzuValue {
        return KuzuValue(nativeList.value)
    }

    actual fun toArray(): Array<KuzuValue> {
        return nativeList.toArray().map { KuzuValue(it) }.toTypedArray()
    }
}