package com.tau.kuzudb

import com.kuzudb.Connection as KuzuConnection
import com.kuzudb.Database as KuzuDatabase
import com.kuzudb.QueryResult as KuzuQueryResult
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.nio.file.Files
import java.nio.file.Paths

@RunWith(MockitoJUnitRunner::class)
class KuzuDBServiceTest {

    @Mock
    private lateinit var mockDatabase: KuzuDatabase

    @Mock
    private lateinit var mockConnection: KuzuConnection

    @Mock
    private lateinit var mockQueryResult: KuzuQueryResult

    private lateinit var kuzuDBService: KuzuDBService

    private val dbPath = "test.db"

    @Before
    fun setUp() {
        kuzuDBService = KuzuDBService()
        val field = kuzuDBService::class.java.getDeclaredField("connection")
        field.isAccessible = true
        val kuzuDBConnection = KuzuDBConnection(dbPath)
        val dbField = kuzuDBConnection::class.java.getDeclaredField("db")
        dbField.isAccessible = true
        dbField.set(kuzuDBConnection, mockDatabase)
        val connField = kuzuDBConnection::class.java.getDeclaredField("conn")
        connField.isAccessible = true
        connField.set(kuzuDBConnection, mockConnection)
        field.set(kuzuDBService, kuzuDBConnection)
    }

    @After
    fun tearDown() {
        Files.deleteIfExists(Paths.get(dbPath))
    }

    @Test
    fun `initialize and close`() {
        kuzuDBService.initialize(dbPath)
        kuzuDBService.close()
    }

    @Test
    fun `create and get node tables`() {
        `when`(mockConnection.query(anyString())).thenReturn(mockQueryResult)
        `when`(mockQueryResult.hasNext()).thenReturn(true, false)
        `when`(mockQueryResult.numColumns).thenReturn(1)
        `when`(mockQueryResult.getColumnName(0)).thenReturn("name")

        val schema = NodeSchema(
            id = 1,
            typeName = "Person",
            properties = listOf(
                PropertyDefinition(key = "name", type = PropertyType.TEXT),
                PropertyDefinition(key = "age", type = PropertyType.NUMBER)
            )
        )
        kuzuDBService.createNodeSchema(schema)
        kuzuDBService.getNodeTables()

        verify(mockConnection).query("CREATE NODE TABLE Person (id STRING, name STRING, age INT64, PRIMARY KEY (id))")
        verify(mockConnection).query("CALL SHOW_TABLES() WHERE type = 'NODE' RETURN name")
    }

    @Test
    fun `create and get edge tables`() {
        `when`(mockConnection.query(anyString())).thenReturn(mockQueryResult)
        `when`(mockQueryResult.hasNext()).thenReturn(true, false)
        `when`(mockQueryResult.numColumns).thenReturn(1)
        `when`(mockQueryResult.getColumnName(0)).thenReturn("name")

        val schema = EdgeSchema(
            id = 1,
            typeName = "Knows",
            properties = listOf(
                PropertyDefinition(key = "since", type = PropertyType.DATE)
            )
        )
        kuzuDBService.createEdgeSchema(schema, "Person", "Person")
        kuzuDBService.getEdgeTables()

        verify(mockConnection).query("CREATE REL TABLE Knows (FROM Person TO Person, since DATE)")
        verify(mockConnection).query("CALL SHOW_TABLES() WHERE type = 'REL' RETURN name")
    }

    @Test
    fun `insert and delete node`() {
        `when`(mockConnection.query(anyString())).thenReturn(mockQueryResult)

        kuzuDBService.insertNode("Person", mapOf("id" to "1", "name" to "Alice", "age" to 30))
        kuzuDBService.deleteNode("Person", "1")

        verify(mockConnection).query("CREATE (n:Person {id: '1', name: 'Alice', age: 30})")
        verify(mockConnection).query("MATCH (n:Person {id: '1'}) DETACH DELETE n")
    }
}