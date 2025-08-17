package com.tau.kuzudb

import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KuzuPreparedStatementTest {

    @Test
    fun testPreparedStatement() = runBlocking {
        KuzuDatabase(":memory:").use { db ->
            KuzuConnection(db).use { conn ->
                conn.query("CREATE NODE TABLE User(name STRING, age INT64, PRIMARY KEY (name))")
                conn.query("CREATE (a:User {name: 'Alice', age: 25})")

                val preparedStatement = conn.prepare("MATCH (u:User) WHERE u.name = \$name RETURN u.age")
                assertTrue(preparedStatement.isSuccess())

                val params = mapOf("name" to KuzuValue("Alice"))
                val result = conn.execute(preparedStatement, params)
                assertTrue(result.isSuccess())
                assertEquals(1, result.getNumTuples())

                val tuple = result.getNext()
                assertEquals(25L, tuple.getValue(0).getValue<Long>())
            }
        }
    }

    @Test
    fun testInvalidPreparedStatement() {
        KuzuDatabase(":memory:").use { db ->
            KuzuConnection(db).use { conn ->
                val preparedStatement = conn.prepare("INVALID QUERY")
                assertEquals(false, preparedStatement.isSuccess())
            }
        }
    }
}