package com.tau.kuzudb

/**
 * A data class for holding advanced KuzuDB configuration settings.
 * TODO: Update Kdoc to reflect new config parameters
 * @param bufferPoolSize The size of the buffer pool in bytes.
 * @param enableCompression Whether to enable compression.
 * @param readOnly Whether to open the database in read-only mode.
 */
data class KuzuDatabaseConfig(
    val databasePath: String?,
    val bufferPoolSize: Long = 1024L,
    val enableCompression: Boolean = true,
    val readOnly: Boolean = false,
    val maxDBSize: Long = 2048L,
    val autoCheckpoint: Boolean = true,
    val checkpointThreshold: Long = 1000L
)