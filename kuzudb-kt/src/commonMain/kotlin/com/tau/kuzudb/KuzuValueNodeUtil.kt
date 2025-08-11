package com.tau.kuzudb

/**
 * A utility object for extracting information from a node value.
 *
 * In Kotlin, a class that only contains static utility methods is
 * best modeled as a singleton `object`.
 */
expect object KuzuValueNodeUtil {
    /**
     * Get the internal ID of the node value.
     */
    fun getID(value: KuzuValue): KuzuInternalID

    /**
     * Get the label name of the node value.
     */
    fun getLabelName(value: KuzuValue): String

    /**
     * Get the property name at the given index from the given node value.
     * Note: A 'String' return type was added, as the function name implies it gets a value.
     */
    fun getPropertyNameAt(value: KuzuValue, index: Long): String

    /**
     * Get the number of properties in the node value.
     */
    fun getPropertySize(value: KuzuValue): Long

    /**
     * Get the property value at the given index from the given node value.
     */
    fun getPropertyValueAt(value: KuzuValue, index: Long): KuzuValue

    /**
     * Convert the node value to its string representation.
     */
    fun toString(value: KuzuValue): String
}