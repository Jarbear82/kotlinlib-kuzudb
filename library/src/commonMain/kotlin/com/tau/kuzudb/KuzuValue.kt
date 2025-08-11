package com.tau.kuzudb

/**
 * An expect class representing a KuzuDB value, designed for Kotlin Multiplatform.
 * This class wraps the underlying Java API for a KuzuDB value.
 * It implements Closeable for resource management.
 */
expect class KuzuValue : AutoCloseable {

    /**
     * A constructor to create a KuzuValue from a given object.
     * The actual implementation will handle the conversion from `Any?`
     * to the appropriate internal representation.
     */
    constructor(value: Any?)

    companion object {
        /**
         * Create a default Value with the given data type.
         */
        fun createDefault(dataType: KuzuDataType): KuzuValue

        /**
         * Create a null Value.
         */
        fun createNull(): KuzuValue

        /**
         * Create a null Value with the given data type.
         */
        fun createNullWithDataType(dataType: KuzuDataType): KuzuValue
    }

    /**
     * Check if the Value has been destroyed.
     */
    fun checkNotDestroyed()

    /**
     * Clone the Value.
     */
    fun clone(): KuzuValue

    /**
     * Copy the Value from another Value.
     */
    fun copy(other: KuzuValue)

    /**
     * Get the data type of the Value.
     */
    fun getDataType(): KuzuDataType

    /**
     * Get the underlying value, casting it to the specified type T.
     * The caller must ensure that T is the correct type.
     *
     * Example: `val myString = kuzuValue.getValue<String>()`
     */
    fun <T> getValue(): T

    /**
     * Check if value is null.
     */
    fun isNull(): Boolean

    /**
     * Internal check for C++ ownership, likely for memory management.
     */
    fun isOwnedByCPP(): Boolean

    /**
     * Set the Value to null.
     */
    fun setNull(flag: Boolean)

    /**
     * Convert the Value to string.
     */
    override fun toString(): String

    /**
     * Close the value and release the underlying resources.
     * This is part of the `Closeable` interface.
     */
    override fun close()
}