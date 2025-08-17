package com.tau.kuzudb

import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KuzuQueryResultTest {

    @Test
    fun testResultIteration() = runBlocking {
        KuzuDatabase(":memory:").use { db ->
            KuzuConnection(db).use { conn ->
                conn.query("CREATE NODE TABLE User(name STRING, age INT64, PRIMARY KEY (name))")
                conn.query("CREATE (a:User {name: 'Alice', age: 25})")
                conn.query("CREATE (b:User {name: 'Bob', age: 30})")

                val result = conn.query("MATCH (u:User) RETURN u.name, u.age ORDER BY u.name")
                assertTrue(result.isSuccess())
                assertEquals(2, result.getNumTuples())
                assertEquals(2, result.getNumColumns())

                // First tuple
                assertTrue(result.hasNext())
                val tuple1 = result.getNext()
                assertEquals("Alice", tuple1.getValue(0).getValue<String>())
                assertEquals(25L, tuple1.getValue(1).getValue<Long>())

                // Second tuple
                assertTrue(result.hasNext())
                val tuple2 = result.getNext()
                assertEquals("Bob", tuple2.getValue(0).getValue<String>())
                assertEquals(30L, tuple2.getValue(1).getValue<Long>())
            }
        }
    }

    @Test
    fun testGetColumnInfo() = runBlocking {
        KuzuDatabase(":memory:").use { db ->
            KuzuConnection(db).use { conn ->
                conn.query("CREATE NODE TABLE User(name STRING, age INT64, PRIMARY KEY (name))")
                val result = conn.query("MATCH (u:User) RETURN u.name, u.age")

                assertEquals("u.name", result.getColumnName(0))
                assertEquals(KuzuDataTypeID.STRING, result.getColumnDataType(0).getID())

                assertEquals("u.age", result.getColumnName(1))
                assertEquals(KuzuDataTypeID.INT64, result.getColumnDataType(1).getID())
            }
        }
    }
}