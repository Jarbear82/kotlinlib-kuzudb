package com.tau.kuzudb

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

class KuzuExceptionTest {

    @Test
    fun testInvalidQueryThrowsException() = runTest {
        KuzuDatabase(":memory:").use { db ->
            KuzuConnection(db).use { conn ->
                assertFailsWith<KuzuException> {
                    conn.query("THIS IS NOT A VALID QUERY")
                }
            }
        }
    }

    @Test
    fun testUsingClosedConnectionThrowsException() = runTest {
        val db = KuzuDatabase(":memory:")
        val conn = KuzuConnection(db)
        conn.close()

        assertFailsWith<KuzuException> {
            conn.query("MATCH (a:User) RETURN a.name")
        }
    }
}