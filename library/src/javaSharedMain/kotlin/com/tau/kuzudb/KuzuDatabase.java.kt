package com.tau.kuzudb

/**
 * JVM/Android implementation of KuzuDatabase.
 */
actual class KuzuDatabase actual constructor(
    path: String,
    config: KuzuDatabaseConfig?
) : AutoCloseable {

    internal val nativeDatabase: com.kuzudb.Database = if (config == null) {
        com.kuzudb.Database(path)
    } else {
        com.kuzudb.Database(path, config.bufferPoolSize, config.enableCompression, config.readOnly)
    }
    actual fun getVersion(): String {
        return com.kuzudb.Database.getVersion()
    }

    actual override fun close() {
        nativeDatabase.close()
    }
}