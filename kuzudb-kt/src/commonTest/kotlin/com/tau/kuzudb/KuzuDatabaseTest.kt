package com.tau.kuzudb

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class KuzuDatabaseTest {

    @Test
    fun testDatabaseCreation() {
        val db = KuzuDatabase(":memory:")
        assertNotNull(db, "Database object should not be null")
    }

    // TODO: Get test to pass
//    @Test
//    fun testDatabaseConfig() {
//        val config = KuzuDatabaseConfig(databasePath = ":memory:", readOnly = false)
//        val db = KuzuDatabase(config)
//        assertNotNull(db, "Database Should Not Be Null")
//    }

    @Test
    fun testUseClosedDatabase() = runTest{
        val db = KuzuDatabase(":memory:")
        db.close()
        // Connect to closed database
        val connection = KuzuConnection(db)
        assertFailsWith<KuzuException> {
            // This should throw an exception
            connection.query("MATCH (n) RETURN (n)")
        }
    }
}