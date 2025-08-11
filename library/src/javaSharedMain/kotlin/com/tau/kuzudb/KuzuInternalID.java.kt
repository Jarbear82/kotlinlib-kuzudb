package com.tau.kuzudb

actual class KuzuInternalID {
    internal val nativeInternalID: com.kuzudb.InternalID

    actual constructor(tableID: Long, offset: Long) {
        this.nativeInternalID = com.kuzudb.InternalID(tableID, offset)
    }
    internal constructor(nativeInternalID: com.kuzudb.InternalID) {
        this.nativeInternalID = nativeInternalID
    }

    actual override fun equals(other: Any?): Boolean {
        if (other !is KuzuInternalID) {
            return false
        }
        return nativeInternalID.equals(other.nativeInternalID)
    }

    actual override fun hashCode() : Int {
        return nativeInternalID.hashCode()
    }

    actual override fun toString() : String {
        return nativeInternalID.toString()
    }
}