package com.tau.kuzudb

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class KuzuConnectionTest {

    @Test
    fun testConnection() {
        val db = KuzuDatabase(":memory:")
        val conn = KuzuConnection(db)
        assertNotNull(conn, "Connection object should not be null")
    }

    @Test
    fun testCreateNodeTableAndQuery() = runTest {
        KuzuDatabase(":memory:").use { db ->
            KuzuConnection(db).use { conn ->
                // 1. Create Schema
                val createTableResult = conn.query("CREATE NODE TABLE User(name STRING, age INT64, PRIMARY KEY (name))")
                assert(createTableResult.isSuccess())

                // 2. Insert Data
                val createNodeResult = conn.query("CREATE (a:User {name: 'Alice', age: 25})")
                assert(createNodeResult.isSuccess())

                // 3. Query Data
                val queryResult = conn.query("MATCH (u:User) RETURN u.name, u.age ORDER BY u.name")
                assert(queryResult.isSuccess())
                assertEquals(1, queryResult.getNumTuples())

                // 4. Verify Results
                assert(queryResult.hasNext())
                val tuple = queryResult.getNext()
                assertEquals("Alice", tuple.getValue(0).getValue<String>())
                assertEquals(25L, tuple.getValue(1).getValue<Long>())
            }
        }
    }

    @Test
    fun testConnectionLifecycle() = runTest {
        val db = KuzuDatabase(":memory:")
        val conn = KuzuConnection(db)
        conn.close()
        assertFailsWith<KuzuException> {
            conn.query("MATCH (a:User) RETURN a.name")
        }
    }

    @Test
    fun testInvalidQuery() = runTest {
        KuzuDatabase(":memory:").use { db ->
            KuzuConnection(db).use { conn ->
                assertFailsWith<KuzuException> {
                    conn.query("INVALID QUERY")
                }
            }
        }
    }

    @Test
    fun testThreadManagement() {
        KuzuDatabase(":memory:").use { db ->
            KuzuConnection(db).use { conn ->
                conn.setMaxNumThreadForExec(4)
                assertEquals(4, conn.getMaxNumThreadForExec())
            }
        }
    }

    @Test
    fun testQueryTimeout() = runTest {
        KuzuDatabase(":memory:").use { db ->
            KuzuConnection(db).use { conn ->
                conn.setQueryTimeout(1) // 1 ms
                assertFailsWith<KuzuException> {
                    // This query will likely take longer than 1ms
                    conn.query("MATCH (a)-[e*1..28]->(b) RETURN a, b;")
                }
            }
        }
    }
}