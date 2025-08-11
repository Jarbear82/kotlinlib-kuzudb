package com.tau.kuzudb

expect class KuzuInternalID {
    constructor(tableID: Long, offset: Long)

    override fun equals(other: Any?) : Boolean

    override fun hashCode() : Int

    override fun toString() : String
}