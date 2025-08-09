package com.tau.kuzudb.value

/**
 * Represents a value in KuzuDB. This is a sealed interface to ensure type safety.
 * Implements [AutoCloseable] for resource management.
 */
sealed interface KuzuValue : AutoCloseable {
    override fun close() {}

    data class BOOL(val nativeValue: Boolean) : KuzuValue
    data class INT8(val nativeValue: Byte) : KuzuValue
    data class INT16(val nativeValue: Short) : KuzuValue
    data class INT32(val nativeValue: Int) : KuzuValue
    data class INT64(val nativeValue: Long) : KuzuValue
    data class UINT8(val nativeValue: UByte) : KuzuValue
    data class UINT16(val nativeValue: UShort) : KuzuValue
    data class UINT32(val nativeValue: UInt) : KuzuValue
    data class UINT64(val nativeValue: ULong) : KuzuValue
    data class FLOAT(val nativeValue: Float) : KuzuValue
    data class DOUBLE(val nativeValue: Double) : KuzuValue
    data class STRING(val nativeValue: String) : KuzuValue
    data class BLOB(val nativeValue: ByteArray) : KuzuValue

    // Should use kotlin's uuid library
    data class KuzuUUID(val nativeValue: String) : KuzuValue

    // kotlinx-datetime would be ideal for these, but for now we'll use Strings
    data class DATE(val nativeValue: String) : KuzuValue
    data class TIMESTAMP(val nativeValue: String) : KuzuValue
    data class INTERVAL(val nativeValue: String) : KuzuValue

    // Complex Types
    data class LIST(val nativeValue: List<KuzuValue>) : KuzuValue
    data class MAP(val nativeValue: Map<String, KuzuValue>) : KuzuValue
    data class STRUCT(val nativeValue: Map<String, KuzuValue>) : KuzuValue

    // Graph Types
    interface KuzuNode : KuzuValue {
        val id: KuzuValue
        val label: String
        val properties: Map<String, KuzuValue>
    }

    interface KuzuRel : KuzuValue {
        val id: KuzuValue
        val label: String
        val src: KuzuValue
        val dst: KuzuValue
        val properties: Map<String, KuzuValue>
    }

    object NULL : KuzuValue
}