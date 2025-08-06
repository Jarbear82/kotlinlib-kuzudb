package io.github.kotlin.kuzudb.scheme

import com.tau.kuzudb.PropertyType
import java.util.UUID

interface Identifiable {
    val id: Any
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