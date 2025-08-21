package com.tau.kuzudb

import java.nio.file.Files
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import java.io.File

class KuzuDatabaseJvmTest {

    @Test
    fun testFileBasedDatabase() {
        val tempDir = Files.createTempDirectory("kuzudb-test-").toFile()
        // Create a file path within the temporary directory
        val dbFile = File(tempDir, "test.db")
        val dbPath = dbFile.absolutePath
        try {
            val db = KuzuDatabase(dbPath)
            assertNotNull(db, "Database object should not be null")
            assertTrue(File(dbPath).exists(), "Database directory should be created")
        } finally {
            tempDir.deleteRecursively()
        }
    }
}