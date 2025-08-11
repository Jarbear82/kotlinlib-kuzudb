package com.tau.kuzudb

actual class KuzuInternalID {
    internal val nativeInternalID: com.kuzudb.InternalID

    actual constructor(tableID: Long, offset: Long) {
        this.nativeInternalID = com.kuzudb.InternalID(tableID, offset)
    }
    internal constructor(nativeInternalID: com.kuzudb.InternalID) {
        this.nativeInternalID = nativeInternalID
    }

    actual fun equals(obj: Object) : Boolean {
        if (obj !is KuzuInternalID) {
            return false
        }
        return nativeInternalID.equals(obj.nativeInternalID)
    }

    actual override fun hashCode() : Int {
        return nativeInternalID.hashCode()
    }

    actual override fun toString() : String {
        return nativeInternalID.toString()
    }
}