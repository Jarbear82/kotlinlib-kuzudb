package com.tau.kuzudb

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