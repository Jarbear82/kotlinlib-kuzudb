package com.tau.kuzudb

/**
 * A data class for holding advanced KuzuDB configuration settings.
 *
 * @property databasePath Path to the database. If the path is empty or equal to ":memory:", the database will be created in memory.
 * @property bufferPoolSize Max size of the buffer pool in bytes.
 * @property enableCompression Enable compression in storage.
 * @property readOnly Open the database in READ_ONLY mode.
 * @property maxDBSize The maximum size of the database in bytes. This is a temporary measure to work around the default 8TB mmap address space limit in some environments.
 * @property autoCheckpoint If true, the database will automatically checkpoint when the size of the WAL file exceeds the checkpoint threshold.
 * @property checkpointThreshold The threshold of the WAL file size in bytes. When the size of the WAL file exceeds this threshold, the database will checkpoint if autoCheckpoint is true.
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