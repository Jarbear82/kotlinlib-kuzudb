package com.tau.kuzudb

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KuzuDBServiceTest {

    private lateinit var kuzuDBService: KuzuDBService
    private val dbPath = "test.db"

    @Before
    fun setUp() {
        kuzuDBService = KuzuDBService()
        kuzuDBService.initialize(dbPath)
    }

    @After
    fun tearDown() {
        kuzuDBService.close()
        File(dbPath).delete()
    }

    @Test
    fun `create and get node tables`() {
        val schema = NodeSchema(
            id = 1,
            typeName = "Person",
            properties = listOf(
                PropertyDefinition(key = "name", type = PropertyType.TEXT),
                PropertyDefinition(key = "age", type = PropertyType.NUMBER)
            )
        )
        kuzuDBService.createNodeSchema(schema)
        val tables = kuzuDBService.getNodeTables()
        assertTrue(tables.any { it["name"] == "Person" })
    }

    @Test
    fun `create and get edge tables`() {
        val personSchema = NodeSchema(
            id = 1,
            typeName = "Person",
            properties = listOf(
                PropertyDefinition(key = "name", type = PropertyType.TEXT),
                PropertyDefinition(key = "age", type = PropertyType.NUMBER)
            )
        )
        kuzuDBService.createNodeSchema(personSchema)

        val knowsSchema = EdgeSchema(
            id = 1,
            typeName = "Knows",
            properties = listOf(
                PropertyDefinition(key = "since", type = PropertyType.DATE)
            )
        )
        kuzuDBService.createEdgeSchema(knowsSchema, "Person", "Person")
        val tables = kuzuDBService.getEdgeTables()
        assertTrue(tables.any { it["name"] == "Knows" })
    }

    @Test
    fun `insert and delete node`() {
        val schema = NodeSchema(
            id = 1,
            typeName = "Person",
            properties = listOf(
                PropertyDefinition(key = "name", type = PropertyType.TEXT),
                PropertyDefinition(key = "age", type = PropertyType.NUMBER)
            )
        )
        kuzuDBService.createNodeSchema(schema)

        val properties = mapOf("id" to "1", "name" to "Alice", "age" to 30)
        kuzuDBService.insertNode("Person", properties)

        val result = kuzuDBService.executeQuery("MATCH (n:Person {id: '1'}) RETURN n.name AS name")
        assertEquals("Alice", result[0]["name"])

        kuzuDBService.deleteNode("Person", "1")
        val deleteResult = kuzuDBService.executeQuery("MATCH (n:Person {id: '1'}) RETURN n.name AS name")
        assertTrue(deleteResult.isEmpty())
    }
}