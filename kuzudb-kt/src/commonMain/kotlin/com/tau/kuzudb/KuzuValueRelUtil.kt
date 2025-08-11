package com.tau.kuzudb

expect object KuzuValueRelUtil {
    /**
     * Get destination id of the given rel value.
     */
    fun getDstID(value: KuzuValue) : KuzuInternalID

    /**
     * Get the id of the given rel value.
     */
    fun getID(value: KuzuValue) : KuzuInternalID

    /**
     * Get the label name of the rel value.
     */
    fun getLabelName(value: KuzuValue) : String

    /**
     * Get the property name at the given index from the given rel value.
     */
    fun getPropertyNameAt(value: KuzuValue, index: Long) : String

    /**
     * Get the property size of the rel value.
     */
    fun getPropertySize(value: KuzuValue) : Long

    /**
     * Get the property value at the given index from the given rel value.
     */
    fun getPropertyValueAt(value: KuzuValue, index: Long) : KuzuValue

    /**
     * Get source id of the given rel value.
     */
    fun getSrcID(value: KuzuValue) : KuzuInternalID

    /**
     * Convert the given rel value to string.
     */
    fun toString(value: KuzuValue) : String
}