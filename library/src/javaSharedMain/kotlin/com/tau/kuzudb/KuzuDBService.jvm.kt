package com.tau.kuzudb

import com.kuzudb.DataTypeID
import com.kuzudb.Value


actual class KuzuDBService {
    private var connection: KuzuDBConnection? = null

    actual fun initialize(dbPath: String) {
        connection = KuzuDBConnection(dbPath)
        connection?.connect()
    }

    actual fun close() {
        connection?.close()
    }

    actual fun createNodeSchema(schema: NodeSchema) {
        val properties = schema.properties.joinToString(", ") {
            "${it.key} ${mapPropertyType(it.type)}"
        }
        val query = "CREATE NODE TABLE ${schema.typeName} (id STRING, $properties, PRIMARY KEY (id))"
        executeQuery(query, "create node table '${schema.typeName}'")
    }

    actual fun createEdgeSchema(schema: EdgeSchema, fromTable: String, toTable: String) {
        val properties = schema.properties.joinToString(", ") {
            "${it.key} ${mapPropertyType(it.type)}"
        }
        val query = "CREATE REL TABLE ${schema.typeName} (FROM $fromTable TO $toTable, $properties)"
        executeQuery(query, "create edge table '${schema.typeName}'")
    }

    actual fun getNodeTables(): List<Map<String, Any?>> {
        val query = "CALL SHOW_TABLES() WHERE type = 'NODE' RETURN name"
        return executeQueryAndParseResults(query, "get node tables")
    }

    actual fun getEdgeTables(): List<Map<String, Any?>> {
        val query = "CALL SHOW_TABLES() WHERE type = 'REL' RETURN name"
        return executeQueryAndParseResults(query, "get edge tables")
    }

    actual fun getTableSchema(tableName: String): List<Map<String, Any?>> {
        val query = "CALL TABLE_INFO('$tableName') RETURN name, type"
        return executeQueryAndParseResults(query, "get table schema for '$tableName'")
    }

    actual fun insertNode(tableName: String, properties: Map<String, Any>): Boolean {
        val propertiesString = properties.entries.joinToString(", ") { (key, value) ->
            "$key: '${value}'"
        }
        val query = "CREATE (n:$tableName {$propertiesString})"
        return executeQuery(query, "insert node into '$tableName'")
    }

    actual fun deleteNode(tableName: String, nodeId: String): Boolean {
        val query = "MATCH (n:$tableName {id: '$nodeId'}) DETACH DELETE n"
        return executeQuery(query, "delete node from '$tableName'")
    }

    actual fun executeQuery(query: String): List<Map<String, Any?>> {
        return executeQueryAndParseResults(query, "custom query")
    }

    private fun executeQuery(query: String, description: String): Boolean {
        return try {
            println("Executing query: $query")
            connection?.conn?.query(query)
            println("Successfully executed query: $description")
            true
        } catch (e: Exception) {
            println("Failed to execute query '$description': ${e.message}")
            e.printStackTrace()
            false
        }
    }

    private fun executeQueryAndParseResults(query: String, description: String): List<Map<String, Any?>> {
        val results = mutableListOf<Map<String, Any?>>()
        try {
            println("Executing query: $query")
            val queryResult = connection?.conn?.query(query)
            queryResult?.let {
                while (it.hasNext()) {
                    val row = it.getNext()
                    val rowMap = mutableMapOf<String, Any?>()
                    for (i in 0 until it.numColumns) {
                        val columnName = it.getColumnName(i)
                        val value = row.getValue(i)
                        rowMap[columnName] = convertKuzuValueToJavaType(value)
                    }
                    results.add(rowMap)
                }
            }
            println("Successfully executed query and parsed results: $description")
        } catch (e: Exception) {
            println("Failed to execute query '$description': ${e.message}")
            e.printStackTrace()
        }
        return results
    }

    private fun convertKuzuValueToJavaType(kuzuValue: Value): Any? {
        return when (kuzuValue.dataType.id) {
            DataTypeID.INT8 -> kuzuValue.getValue<Byte>()
            DataTypeID.INT16 -> kuzuValue.getValue<Short>()
            DataTypeID.INT32 -> kuzuValue.getValue<Int>()
            DataTypeID.INT64 -> kuzuValue.getValue<Long>()
            DataTypeID.FLOAT -> kuzuValue.getValue<Float>()
            DataTypeID.DOUBLE -> kuzuValue.getValue<Double>()
            DataTypeID.BOOL -> kuzuValue.getValue<Boolean>()
            DataTypeID.STRING -> kuzuValue.getValue<String>()
            DataTypeID.DATE -> kuzuValue.getValue<String>()
            DataTypeID.TIMESTAMP -> kuzuValue.getValue<String>()
            DataTypeID.INTERVAL -> kuzuValue.getValue<String>()
            DataTypeID.LIST -> {
                val listValues = kuzuValue.getValue<List<Value>>()
                listValues.map { element -> convertKuzuValueToJavaType(element) }
            }
            DataTypeID.STRUCT -> {
                val structMap = mutableMapOf<String, Any?>()
                kuzuValue.getValue<Map<String, Value>>().forEach { (key, structValue) ->
                    structMap[key] = convertKuzuValueToJavaType(structValue)
                }
                structMap
            }
            DataTypeID.MAP -> {
                val mapValues = mutableMapOf<String, Any?>()
                kuzuValue.getValue<Map<String, Value>>().forEach { (key, mapValue) ->
                    mapValues[key] = convertKuzuValueToJavaType(mapValue)
                }
                mapValues
            }
            else -> {
                println("Unhandled Kuzu data type in conversion: ${kuzuValue.dataType.id}")
                null
            }
        }
    }

    private fun mapPropertyType(type: PropertyType): String {
        return when (type) {
            PropertyType.TEXT, PropertyType.LONG_TEXT, PropertyType.IMAGE -> "STRING"
            PropertyType.NUMBER -> "INT64"
            PropertyType.BOOLEAN -> "BOOLEAN"
            PropertyType.DATE -> "DATE"
            PropertyType.TIMESTAMP -> "TIMESTAMP"
            PropertyType.LIST, PropertyType.MAP, PropertyType.VECTOR, PropertyType.STRUCT -> "STRING"
        }
    }
}