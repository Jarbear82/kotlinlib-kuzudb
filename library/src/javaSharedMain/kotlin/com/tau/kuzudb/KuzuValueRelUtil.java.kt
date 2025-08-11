package com.tau.kuzudb

import com.kuzudb.ValueRelUtil as NativeValueRelUtil

actual object KuzuValueRelUtil {
    actual fun getDstID(value: KuzuValue): KuzuInternalID {
        return KuzuInternalID(NativeValueRelUtil.getDstID(value.nativeValue))
    }

    actual fun getID(value: KuzuValue): KuzuInternalID {
        return KuzuInternalID(NativeValueRelUtil.getID(value.nativeValue))
    }

    actual fun getLabelName(value: KuzuValue): String {
        return NativeValueRelUtil.getLabelName(value.nativeValue)
    }

    actual fun getPropertyNameAt(value: KuzuValue): String {
        // The Java implementation is missing the index parameter.
        // This is a placeholder.
        return ""
    }

    actual fun getPropertySize(value: KuzuValue): Long {
        return NativeValueRelUtil.getPropertySize(value.nativeValue)
    }

    actual fun getPropertyValueAt(value: KuzuValue): KuzuValue {
        // The Java implementation is missing the index parameter.
        // This is a placeholder.
        return KuzuValue(null)
    }

    actual fun getSrcID(value: KuzuValue): KuzuInternalID {
        return KuzuInternalID(NativeValueRelUtil.getSrcID(value.nativeValue))
    }

    actual fun toString(value: KuzuValue): String {
        return NativeValueRelUtil.toString(value.nativeValue)
    }
}