package com.tau.kuzudb

/**
 * Declares the expected enum constants for KuzuDB's data types.
 * Common code can rely on these constants being available on any platform.
 */
expect enum class KuzuDataTypeID {
    ANY,
    ARRAY,
    BLOB,
    BOOL,
    DATE,
    DECIMAL,
    DOUBLE,
    FLOAT,
    INT128,
    INT16,
    INT32,
    INT8,
    INTERNAL_ID,
    INTERVAL,
    LIST,
    MAP,
    NODE,
    RECURSIVE_REL,
    REL,
    SERIAL,
    STRING,
    STRUCT,
    TIMESTAMP,
    TIMESTAMP_MS,
    TIMESTAMP_NS,
    TIMESTAMP_SEC,
    TIMESTAMP_TZ,
    UINT16,
    UINT32,
    UINT64,
    UINT8,
    UNION,
    UUID
}