package com.tau.kuzudb

import com.kuzudb.Database

/**
 * JVM/Android implementation of KuzuDatabase.
 */
actual class KuzuDatabase : AutoCloseable {
    internal val nativeDatabase: Database

    actual constructor() {
        try {
            nativeDatabase = Database()
        } catch (e: RuntimeException) {
            throw KuzuException(e.message ?: "Unknown Kuzu Database Error", e)
        }
    }

    actual constructor(path: String) {
        try {
            nativeDatabase = Database(path)
        } catch (e: RuntimeException) {
            throw KuzuException(e.message ?: "Unknown Kuzu Database Error", e)
        }
    }

    // TODO: Get Test to pass
//    actual constructor(config: KuzuDatabaseConfig) {
//        try {
//            nativeDatabase = Database(
//                config.databasePath,
//                config.bufferPoolSize,
//                config.enableCompression,
//                config.readOnly,
//                config.maxDBSize,
//                config.autoCheckpoint,
//                config.checkpointThreshold
//            )
//        } catch (e: RuntimeException) {
//            throw KuzuException(e.message ?: "Unknown Kuzu Database Error", e)
//        }
//    }

    actual override fun close() {
        try {
            nativeDatabase.close()
        } catch (e: RuntimeException) {
            throw KuzuException(e.message ?: "Unknown Kuzu Database Error", e)
        }
    }
}