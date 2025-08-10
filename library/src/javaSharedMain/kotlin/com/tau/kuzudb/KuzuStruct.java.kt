package com.tau.kuzudb

actual class KuzuStruct : AutoCloseable {
    /**
     * Construct a struct from a value
     */
    constructor(value: KuzuValue)

    /**
     * Construct a struct literal from a given set of fields.
     */
    constructor(fieldNames: Array<String>, fieldValues: Array<KuzuValue>)

    /**
     * Construct a struct literal from a given set of fields represented as a Kotlin map.
     */
    constructor(fields: Map<String, KuzuValue>)

    /**
     * Closes this object, relinquishing the underlying value
     */
    override fun close()

    /**
     * Get the name of the field at the given index
     */
    fun getFieldNameByIndex(index: Long) : String

    /**
     * Get the index of the field with the given name
     */
    fun getIndexByFieldName(fieldName: String) : Long

    fun getNumFields() : Long

    fun getValue() : KuzuValue

    /**
     * Get the value of the field with the given name
     */
    fun getValueByFieldName(fieldName: String) : KuzuValue

    /**
     * Get the value of the field at the given index
     */
    fun getValueByIndex(inde: Long) : KuzuValue

    /**
     * Gets the elements the struct as a Kotlin map.
     */
    fun toMap() : Map<String, KuzuValue>
}