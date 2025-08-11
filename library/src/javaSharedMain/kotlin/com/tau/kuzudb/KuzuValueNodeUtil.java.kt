package com.tau.kuzudb

import com.kuzudb.ValueNodeUtil as NativeValueNodeUtil

actual object KuzuValueNodeUtil {
    actual fun getID(value: KuzuValue): KuzuInternalID {
        return KuzuInternalID(NativeValueNodeUtil.getID(value.nativeValue))
    }

    actual fun getLabelName(value: KuzuValue): String {
        return NativeValueNodeUtil.getLabelName(value.nativeValue)
    }

    actual fun getPropertyNameAt(value: KuzuValue, index: Long): String {
        return NativeValueNodeUtil.getPropertyNameAt(value.nativeValue, index)
    }

    actual fun getPropertySize(value: KuzuValue): Long {
        return NativeValueNodeUtil.getPropertySize(value.nativeValue)
    }

    actual fun getPropertyValueAt(value: KuzuValue, index: Long): KuzuValue {
        return KuzuValue(NativeValueNodeUtil.getPropertyValueAt(value.nativeValue, index))
    }

    actual fun toString(value: KuzuValue): String {
        return NativeValueNodeUtil.toString(value.nativeValue)
    }
}