package com.tau.kuzudb.value

import kotlin.io.Closeable

/**
 * Represents a value in KuzuDB. This is a sealed interface to ensure type safety.
 * Implements [Closeable] for resource management.
 */
sealed interface KuzuValue : Closeable {
    data class BOOL(val value: Boolean) : KuzuValue {
        override fun close() {}
    }

    data class INT64(val value: Long) : KuzuValue {
        override fun close() {}
    }
    // ... other primitive types

    data class STRING(val value: String) : KuzuValue {
        override fun close() {}
    }
    // ... other complex types
}