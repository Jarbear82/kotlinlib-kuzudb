package com.tau.kuzudb

expect class KuzuDataType : AutoCloseable {
    constructor(id: KuzuDataTypeID)

    constructor(
        id: KuzuDataTypeID,
        childTypeId: KuzuDataTypeID,
        numElementsInArray: Long
    )

    fun clone(): KuzuDataType

    override fun close()

    fun equals(other: KuzuDataType): Boolean

    fun getChildType() : KuzuDataType

    fun getFixedNumElementsInList() : Long

    fun getID() : KuzuDataTypeID
}