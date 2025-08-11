package com.tau.kuzudb

import com.kuzudb.ValueRecursiveRelUtil as NativeValueRecursiveRelUtil

actual object KuzuValueRecursiveRelUtil {
    actual fun getNodeList(value: KuzuValue): KuzuValue {
        return KuzuValue(NativeValueRecursiveRelUtil.getNodeList(value.nativeValue))
    }

    actual fun getRelList(value: KuzuValue): KuzuValue {
        return KuzuValue(NativeValueRecursiveRelUtil.getRelList(value.nativeValue))
    }
}