package com.tau.kuzudb.value

/**
 * Represents a value in KuzuDB. This is a sealed interface to ensure type safety.
 * Implements [AutoCloseable] for resource management.
 */
sealed interface KuzuValue : AutoCloseable {
    override fun close() {}

    data class BOOL(val value: Boolean) : KuzuValue
    data class INT8(val value: Byte) : KuzuValue
    data class INT16(val value: Short) : KuzuValue
    data class INT32(val value: Int) : KuzuValue
    data class INT64(val value: Long) : KuzuValue
    data class UINT8(val value: UByte) : KuzuValue
    data class UINT16(val value: UShort) : KuzuValue
    data class UINT32(val value: UInt) : KuzuValue
    data class UINT64(val value: ULong) : KuzuValue
    data class FLOAT(val value: Float) : KuzuValue
    data class DOUBLE(val value: Double) : KuzuValue
    data class STRING(val value: String) : KuzuValue
    data class BLOB(val value: ByteArray) : KuzuValue

    // Should use kotlin's uuid library
    data class KuzuUUID(val value: String) : KuzuValue

    // kotlinx-datetime would be ideal for these, but for now we'll use Strings
    data class DATE(val value: String) : KuzuValue
    data class TIMESTAMP(val value: String) : KuzuValue
    data class INTERVAL(val value: String) : KuzuValue

    // Complex Types
    data class LIST(val value: List<KuzuValue>) : KuzuValue
    data class MAP(val value: Map<String, KuzuValue>) : KuzuValue
    data class STRUCT(val value: Map<String, KuzuValue>) : KuzuValue

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