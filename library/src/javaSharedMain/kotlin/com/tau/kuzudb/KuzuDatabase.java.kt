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
            databsePath=config.databasePath,
            bufferPoolSize=config.bufferPoolSize,
            enableCompression=config.enableCompression,
            readOnly=config.readOnly,
            maxDBSize=config.maxDBSize,
            autoCheckpoint=config.autoCheckpoint,
            checkpointThreshhold=config.checkpointThreshold
        )
    }

    actual override fun close() {
        nativeDatabase.close()
    }
}