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

    actual fun getPropertyNameAt(value: KuzuValue, index: Long): String {
        return NativeValueRelUtil.getPropertyNameAt(value.nativeValue, index)
    }

    actual fun getPropertySize(value: KuzuValue): Long {
        return NativeValueRelUtil.getPropertySize(value.nativeValue)
    }

    actual fun getPropertyValueAt(value: KuzuValue, index: Long): KuzuValue {
        return KuzuValue(NativeValueRelUtil.getPropertyValueAt(value.nativeValue, index))
    }

    actual fun getSrcID(value: KuzuValue): KuzuInternalID {
        return KuzuInternalID(NativeValueRelUtil.getSrcID(value.nativeValue))
    }

    actual fun toString(value: KuzuValue): String {
        return NativeValueRelUtil.toString(value.nativeValue)
    }
}