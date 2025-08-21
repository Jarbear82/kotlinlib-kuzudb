package com.tau.kuzudb

expect class KuzuList : AutoCloseable {
    constructor(type: KuzuDataType, numElements: Long)

    constructor(value: KuzuValue)

    constructor(values: Array<KuzuValue>)



    override fun close()

    fun getListElement(index: Long) : KuzuValue

    fun getListSize() : Long

    fun getValue() : KuzuValue

    fun toArray() : Array<KuzuValue>
}