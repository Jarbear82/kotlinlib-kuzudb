package com.tau.kuzudb

expect class KuzuInternalID {
    constructor(tableID: Long, offset: Long)

    fun equals(obj: Object) : Boolean

    override fun hashCode() : Int

    override fun toString() : String
}