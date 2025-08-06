# KuzuDB-Kotlin

[![official project](http.jb.gg/badges/official.svg)](https://github.com/JetBrains#jetbrains-on-github)

## What is it?

This repository contains a Kotlin Multiplatform library for interacting with the [KuzuDB](https://kuzudb.com/) graph database. The library provides a unified API for Android and JVM platforms, with plans to support more platforms in the future.

## Installation

To use this library in your project, add the following to your `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("io.github.jarbear82:kuzudb-kt:0.0.1")
}
```

## Usage

Here's a quick example of how to use the library:
```Kotlin

import com.tau.kuzudb.KuzuDBService
import com.tau.kuzudb.NodeSchema
import com.tau.kuzudb.PropertyDefinition
import com.tau.kuzudb.PropertyType

fun main() {
val kuzuDBService = KuzuDBService()
kuzuDBService.initialize("test.db")

    // Create a node schema
    val personSchema = NodeSchema(
        id = 1,
        typeName = "Person",
        properties = listOf(
            PropertyDefinition(key = "name", type = PropertyType.TEXT),
            PropertyDefinition(key = "age", type = PropertyType.NUMBER)
        )
    )
    kuzuDBService.createNodeSchema(personSchema)

    // Insert a node
    val properties = mapOf("id" to "1", "name" to "Alice", "age" to 30)
    kuzuDBService.insertNode("Person", properties)

    // Query the database
    val result = kuzuDBService.executeQuery("MATCH (n:Person) RETURN n.name AS name")
    println(result)

    kuzuDBService.close()
}
```

## API Reference

`KuzuDBService`

- initialize(dbPath: String): Initializes the KuzuDB database.
- close(): Closes the database connection.
- createNodeSchema(schema: NodeSchema): Creates a new node table.
- createEdgeSchema(schema: EdgeSchema, fromTable: String, toTable: String): Creates a new relationship table.
- getNodeTables(): List<Map<String, Any?>>: Retrieves a list of all node tables.
- getEdgeTables(): List<Map<String, Any?>>: Retrieves a list of all relationship tables.
- getTableSchema(tableName: String): List<Map<String, Any?>>: Retrieves the schema for a given table.
- insertNode(tableName: String, properties: Map<String, Any>): Boolean: Inserts a new node into a table.
- deleteNode(tableName: String, nodeId: String): Boolean: Deletes a node from a table.
- executeQuery(query: String): List<Map<String, Any?>>: Executes a Cypher query.

## Error Handling

The library uses a custom exception class, KuzuException, to report errors. You can catch this exception to handle errors in your application:
```Kotlin

try {
kuzuDBService.executeQuery("invalid query")
} catch (e: KuzuException) {
println("Error executing query: ${e.message}")
}
```

## Contributing

Contributions are welcome! If you find a bug or have a feature request, please open an issue on the GitHub repository.