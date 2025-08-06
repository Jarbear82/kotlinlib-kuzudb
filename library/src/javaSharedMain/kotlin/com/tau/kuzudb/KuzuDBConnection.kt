package com.tau.kuzudb

import com.kuzudb.Connection
import com.kuzudb.Database
import java.nio.file.Files
import java.nio.file.Paths

class KuzuDBConnection(private val dbPath: String) {
    private var db: Database? = null
    internal var conn: Connection? = null

    fun connect() {
        try {
            val dbDirectory = Paths.get(dbPath).parent
            if (!Files.exists(dbDirectory)) {
                Files.createDirectories(dbDirectory)
            }
            db = Database(dbPath)
            conn = Connection(db)
            println("KuzuDB initialized successfully at: $dbPath")
        } catch (e: Exception) {
            println("Failed to initialize KuzuDB: ${e.message}")
            e.printStackTrace()
        }
    }

    fun close() {
        try {
            conn?.close()
            db?.close()
            println("KuzuDB connection closed.")
        } catch (e: Exception) {
            println("Failed to close KuzuDB connection: ${e.message}")
        }
    }
}