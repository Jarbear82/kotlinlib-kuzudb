package com.tau.kuzudb

enum class PropertyType(val displayName: String) {
    ANY("Any Data Type"),
    INT8("8 Bit Integer"),
    INT16("16 Bit Integer"),
    INT32("32 Bit Integer"),
    INT64("64 Bit Integer"),
    INT128("128 Bit Integer"),
    UINT8("Unsigned 8 Bit Integer"),
    UINT16("Unsigned 16 Bit Integer"),
    UINT32("Unsigned 32 Bit Integer"),
    UINT64("Unsigned 64 Bit Integer"),
    FLOAT("Single Precision Floating-Point Number"),
    DOUBLE("Double Precision Floating-Point Number"),
    // ToDo: Decimal allows you to specify the precision
    DECIMAL("Arbitrary Fixed Precision Number"),
    BOOLEAN("Boolean"),
    UUID("Signed Sixteen-Byte Integer (INT128)"),
    STRING("Variable Length Character String"),
    NULL("Special Value To Represent Unknown Data"),
    DATE("Year, Month, Day (YYYY-MM-DD)"),
    TIMESTAMP("Combination Of Time And Date (YYYY-MM-DD hh:mm:ss)"),
    INTERVAL("Date/Time Difference"),
    STRUCT("STRUCT(first STRING, last STRING)"),
    MAP("MAP(STRING, INT64)"),
    UNION("UNION(price FLOAT, note STRING)"),
    BLOB("Arbitrary Binary Object"),
    SERIAL("Logical Auto Incrementing"),
    NODE("Represents A Node In A Graph"),
    REL("Represents A Relationship In A Graph"),
    RECURSIVE_REL("Represents Recrusive Relationships"),
    LIST("Variable Length List"),
    ARRAY("Fixed Length List"),
    JSON("JSON Native Data Type (Requires JSON Extension)")
}

val PropertyType.defaultValue: Any?
    get() = when (this) {
        PropertyType.ANY -> Any,
        PropertyType.INT8 -> Byte,
        PropertyType.INT16 -> Int,
        PropertyType.INT32 -> Short,
        PropertyType.INT64 -> Long,
        // No 128 Bit type in Kotlin
        // PropertyType.INT128,
        PropertyType.UINT8 -> UByte,
        PropertyType.UINT16 -> UShort,
        PropertyType.UINT32 -> UInt,
        PropertyType.UINT64 -> ULong,
        PropertyType.FLOAT -> Float,
        PropertyType.DOUBLE -> Double,
        // ToDo: Decimal allows you to specify the precision
        // PropertyType.DECIMAL,
        PropertyType.BOOLEAN -> Boolean,
        PropertyType.UUID -> Kotlin.uuid,
        PropertyType.STRING -> String,
        PropertyType.NULL -> null,
        // The following Requiere specific formatting
        //   - PropertyType.DATE -> String,
        //   - PropertyType.TIMESTAMP,
        //   - PropertyType.INTERVAL,
        PropertyType.STRUCT,
        PropertyType.MAP,
        PropertyType.UNION,
        PropertyType.BLOB,
        PropertyType.SERIAL,
        PropertyType.NODE,
        PropertyType.REL,
        PropertyType.RECURSIVE_REL,
        PropertyType.LIST,
        PropertyType.ARRAY,
        PropertyType.JSON
    }
