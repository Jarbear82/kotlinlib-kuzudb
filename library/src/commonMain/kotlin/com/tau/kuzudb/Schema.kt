package io.github.kotlin.kuzudb.scheme

import java.util.UUID

interface Identifiable {
    val id: Any
}

enum class PropertyType(val displayName: String) {
    TEXT("Text"),
    NUMBER("Number"),
    BOOLEAN("Boolean"),
    LONG_TEXT("Long Text"),
    IMAGE("Image"),
    DATE("Date"),
    TIMESTAMP("Timestamp"),
    LIST("List"),
    MAP("Map"),
    VECTOR("Vector"),
    STRUCT("Struct")
}

val PropertyType.defaultValue: Any?
    get() = when (this) {
        PropertyType.TEXT, PropertyType.LONG_TEXT, PropertyType.IMAGE -> ""
        PropertyType.NUMBER -> 0L // Defaulting to Long for INT64
        PropertyType.BOOLEAN -> false
        PropertyType.DATE -> "2023-01-01" // KuzuDB expects date as string
        PropertyType.TIMESTAMP -> System.currentTimeMillis()
        PropertyType.LIST -> "[]"
        PropertyType.MAP -> "{}"
        PropertyType.VECTOR -> "[]"
        PropertyType.STRUCT -> "{}"
    }

data class PropertyDefinition(
    val id: String = UUID.randomUUID().toString(),
    var key: String,
    var type: PropertyType,
    var isIndexed: Boolean = false,
    var isFullTextIndexed: Boolean = false
)

sealed interface SchemaDefinition : Identifiable {
    override val id: Int
    val typeName: String
    val properties: List<PropertyDefinition>
    val allowSemiStructured: Boolean
}

data class NodeSchema(
    override val id: Int,
    override var typeName: String,
    override var properties: List<PropertyDefinition>,
    override var allowSemiStructured: Boolean = false
) : SchemaDefinition

data class EdgeSchema(
    override val id: Int,
    override var typeName: String,
    override var properties: List<PropertyDefinition>,
    override var allowSemiStructured: Boolean = false
) : SchemaDefinition