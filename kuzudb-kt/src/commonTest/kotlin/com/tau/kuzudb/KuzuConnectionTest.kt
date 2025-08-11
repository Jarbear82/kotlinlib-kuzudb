package com.tau.kuzudb

import kotlin.test.Test
import kotlin.test.assertNotNull

class KuzuConnectionTest {

    @Test
    fun testConnection() {
        val db = KuzuDatabase(":memory:")
        val conn = KuzuConnection(db)
        assertNotNull(conn, "Connection object should not be null")
    }
}