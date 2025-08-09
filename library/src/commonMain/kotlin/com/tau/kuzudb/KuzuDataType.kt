package com.tau.kuzudb

expect class KuzuDataType : AutoCloseable {
    constructor(id: KuzuDataTypeID)

    constructor(
        id: KuzuDataTypeID,
        childType: KuzuDataTypeID,
        numElementsInArray: Long
    )

    override fun clone() : KuzuDataType

    override fun close()

    override fun equals() : Boolean

    override fun getChildType() : KuzuDataType

    override fun getFixedNumElementsInList() : Long

    override fun getID() : KuzuDataTypeID
}