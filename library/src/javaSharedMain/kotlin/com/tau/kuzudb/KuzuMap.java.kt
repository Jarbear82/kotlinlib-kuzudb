package com.tau.kuzudb

actual class KuzuMap : AutoCloseable {
    constructor(value: KuzuValue)

    constructor(keys: Array<KuzuValue>, values: Array<KuzuValue>)

    override fun close()

    fun getKey(index: Long) : KuzuValue

    fun getNumFields() : Long

    fun getValue() : KuzuValue

    fun getValue(index: Long) : KuzuValue
}