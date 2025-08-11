package com.tau.kuzudb

import com.kuzudb.DataType
import com.kuzudb.DataTypeID

actual class KuzuDataType : AutoCloseable {
    internal val nativeDataType: DataType

    // Private constructor for internal wrapping
    internal constructor(nativeDataType: DataType) {
        this.nativeDataType = nativeDataType
    }

    actual constructor(id: KuzuDataTypeID) {
        this.nativeDataType = DataType(id.nativeType)
    }

    actual constructor(
        id: KuzuDataTypeID,
        childTypeId: KuzuDataTypeID,
        numElementsInArray: Long
    ) {
        this.nativeDataType = DataType(id.nativeType, KuzuDataType(childTypeId).nativeDataType, numElementsInArray)
    }

    actual fun getID(): KuzuDataTypeID {
        return KuzuDataTypeID.fromNative(nativeDataType.id)
    }

    actual fun getChildType(): KuzuDataType {
        return try {
            val nativeChildType = nativeDataType.getChildType()
            KuzuDataType(nativeChildType)
        } catch (e: RuntimeException) {
            throw KuzuException("Failed to get child type", e)
        }
    }

    actual fun getFixedNumElementsInList(): Long {
        return try {
            nativeDataType.getFixedNumElementsInList()
        } catch (e: RuntimeException) {
            throw KuzuException("Failed to get fixed number of elements in list", e)
        }
    }

    actual fun clone(): KuzuDataType {
        return KuzuDataType(nativeDataType.clone())
    }

    actual fun equals(other: KuzuDataType): Boolean {
        return nativeDataType.equals(other.nativeDataType)
    }

    actual override fun close() {
        try {
            nativeDataType.close()
        } catch (e: RuntimeException) {
            throw KuzuException("Failed to close KuzuDataType", e)
        }
    }
}