package com.tau.kuzudb

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class KuzuDatabaseTest {

    @Test
    fun testDatabaseCreation() {
        val db = KuzuDatabase(":memory:")
        assertNotNull(db, "Database object should not be null")
    }

    @Test
    fun testDatabaseConfig() {
        val config = KuzuDatabaseConfig(databasePath = ":memory:", readOnly = false)
        KuzuDatabase(config).use { db ->
            assertNotNull(db, "Database object should not be null")
        }
    }

    @Test
    fun testUseClosedDatabase() {
        val db = KuzuDatabase(":memory:")
        db.close()
        assertFailsWith<KuzuException> {
            // This should throw an exception
            KuzuConnection(db)
        }
    }
}