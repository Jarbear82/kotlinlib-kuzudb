package com.tau.kuzudb

import com.kuzudb.Database

/**
 * JVM/Android implementation of KuzuDatabase.
 */
actual class KuzuDatabase : AutoCloseable {
    internal val nativeDatabase: Database

    actual constructor() {
        nativeDatabase = Database()
    }

    actual constructor(path: String) {
        nativeDatabase = Database(path)
    }

    actual constructor(config: KuzuDatabaseConfig) {
        nativeDatabase = Database(
            config.databasePath,
            config.bufferPoolSize,
            config.enableCompression,
            config.readOnly,
            config.maxDBSize,
            config.autoCheckpoint,
            config.checkpointThreshold
        )
    }

    actual override fun close() {
        nativeDatabase.close()
    }
}