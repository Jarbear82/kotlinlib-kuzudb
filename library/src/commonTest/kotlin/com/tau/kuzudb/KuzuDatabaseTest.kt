package com.tau.kuzudb

import kotlin.test.Test
import kotlin.test.assertNotNull

class KuzuDatabaseTest {

    @Test
    fun testDatabaseCreation() {
        val db = KuzuDatabase(":memory:")
        assertNotNull(db, "Database object should not be null")
    }
}