package com.tau.kuzudb

import com.kuzudb.KuzuStruct as NativeKuzuStruct

actual class KuzuStruct : AutoCloseable {
    internal val nativeStruct: NativeKuzuStruct
    /**
     * Construct a struct from a value
     */
    actual constructor(value: KuzuValue) {
        this.nativeStruct = NativeKuzuStruct(value.nativeValue)
    }

    /**
     * Construct a struct literal from a given set of fields.
     */
    actual constructor(fieldNames: Array<String>, fieldValues: Array<KuzuValue>) {
        this.nativeStruct = NativeKuzuStruct(fieldNames, fieldValues.map { it.nativeValue }.toTypedArray())
    }

    /**
     * Construct a struct literal from a given set of fields represented as a Kotlin map.
     */
    actual constructor(fields: Map<String, KuzuValue>) {
        this.nativeStruct = NativeKuzuStruct(fields.mapValues { it.value.nativeValue })
    }

    internal constructor(nativeStruct: NativeKuzuStruct) {
        this.nativeStruct = nativeStruct
    }

    /**
     * Closes this object, relinquishing the underlying value
     */
    actual override fun close() {
        nativeStruct.close()
    }

    /**
     * Get the name of the field at the given index
     */
    actual fun getFieldNameByIndex(index: Long): String {
        return nativeStruct.getFieldNameByIndex(index)
    }

    /**
     * Get the index of the field with the given name
     */
    actual fun getIndexByFieldName(fieldName: String): Long {
        return nativeStruct.getIndexByFieldName(fieldName)
    }

    actual fun getNumFields(): Long {
        return nativeStruct.numFields
    }

    actual fun getValue(): KuzuValue {
        return KuzuValue(nativeStruct.value)
    }

    /**
     * Get the value of the field with the given name
     */
    actual fun getValueByFieldName(fieldName: String): KuzuValue {
        return KuzuValue(nativeStruct.getValueByFieldName(fieldName))
    }

    /**
     * Get the value of the field at the given index
     */
    actual fun getValueByIndex(index: Long): KuzuValue {
        return KuzuValue(nativeStruct.getValueByIndex(index))
    }

    /**
     * Gets the elements the struct as a Kotlin map.
     */
    actual fun toMap(): Map<String, KuzuValue> {
        return nativeStruct.toMap().mapValues { KuzuValue(it.value) }
    }
}