package com.tau.kuzudb

import com.kuzudb.DataType
import com.kuzudb.DataTypeID

actual class KuzuDataType : AutoCloseable {
    internal val nativeDataType: DataType

    actual constructor(id: KuzuDataTypeID) {
        nativeDataType = DataType(id.nativeType)
    }

    actual constructor(
        id: KuzuDataTypeID,
        childType: KuzuDataTypeID,
        numElementsInArray: Long
    ) {
        nativeDataType = DataType(id.nativeType, childType.nativeType, numElementsInArray)
    }

    actual override fun clone() : KuzuDataType {
        try {
            val id = nativeDataType.id
            return KuzuDataType(id)
        }
    }

    actual override fun close() {
        try {
            nativeDataType.close()
        } catch (e: RuntimeException) {
            throw KuzuException(e.message ?: "Unkown Kuzu Data Type Exception")
        }
    }

    actual override fun equals() : Boolean

    actual override fun getChildType() : KuzuDataType

    actual override fun getFixedNumElementsInList() : Long

    actual override fun getID() : KuzuDataTypeID
}