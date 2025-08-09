package com.tau.kuzudb

/**
 * A data class for holding advanced KuzuDB configuration settings.
 *
 * @param bufferPoolSize The size of the buffer pool in bytes.
 * @param enableCompression Whether to enable compression.
 * @param readOnly Whether to open the database in read-only mode.
 */
data class KuzuDatabaseConfig(
    val bufferPoolSize: Long = 0, // 0 for default
    val enableCompression: Boolean = true,
    val readOnly: Boolean = false,
)