package com.tau.kuzudb

import java.nio.file.Files
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class KuzuDatabaseJvmTest {

    @Test
    fun testFileBasedDatabase() {
        val tempDir = Files.createTempDirectory("kuzudb-test-").toFile()
        val dbPath = tempDir.path
        try {
            val db = KuzuDatabase(dbPath)
            assertNotNull(db, "Database object should not be null")
            assertTrue(tempDir.exists(), "Database directory should be created")
        } finally {
            tempDir.deleteRecursively()
        }
    }
}