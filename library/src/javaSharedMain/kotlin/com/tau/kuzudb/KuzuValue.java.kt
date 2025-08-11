package com.tau.kuzudb

import com.kuzudb.Value

actual class KuzuValue : AutoCloseable {
    internal val nativeValue : Value

    internal constructor(nativeValue: Value) {
        this.nativeValue = nativeValue
    }
    actual constructor(value: Any?) {
        this.nativeValue = Value(value)
    }

    actual companion object {
        actual fun createDefault(dataType: KuzuDataType): KuzuValue {
            return KuzuValue(Value.createDefault(dataType.nativeDataType))
        }

        actual fun createNull(): KuzuValue {
            return KuzuValue(Value.createNull())
        }

        actual fun createNullWithDataType(dataType: KuzuDataType): KuzuValue {
            return KuzuValue(Value.createNullWithDataType(dataType.nativeDataType))
        }
    }

    actual fun checkNotDestroyed() {
        try {
            nativeValue.checkNotDestroyed()
        } catch (e: RuntimeException) {
            throw KuzuException(e.message ?: "Unknown Kuzu Value Exception")
        }
    }

    actual fun clone(): KuzuValue {
        return KuzuValue(nativeValue.clone())
    }

    actual fun copy(other: KuzuValue) {
        nativeValue.copy(other.nativeValue)
    }

    actual fun getDataType(): KuzuDataType {
        return KuzuDataType(nativeValue.dataType)
    }

    actual fun <T> getValue(): T {
        return nativeValue.value as T
    }

    actual fun isNull(): Boolean {
        return nativeValue.isNull
    }

    actual fun isOwnedByCPP(): Boolean {
        return nativeValue.isOwnedByCPP
    }

    actual fun setNull(flag: Boolean) {
        nativeValue.setNull(flag)
    }

    actual override fun toString(): String {
        return nativeValue.toString()
    }

    actual override fun close() {
        try {
            nativeValue.close()
        } catch (e: RuntimeException) {
            throw KuzuException(e.message ?: "Unknown Kuzu Value Error")
        }
    }
}