package com.tau.kuzudb

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class KuzuValueTest {

    @Test
    fun testValueCreation() {
        val value = KuzuValue("hello")
        assertNotNull(value, "Value object should not be null")
        assertEquals("hello", value.getValue<String>(), "Value should be 'hello'")
    }

    @Test
    fun testAllDataTypes() {
        assertEquals(true, KuzuValue(true).getValue())
        assertEquals(123.toShort(), KuzuValue(123.toShort()).getValue())
        assertEquals(123, KuzuValue(123).getValue())
        assertEquals(123L, KuzuValue(123L).getValue())
        assertEquals(1.23f, KuzuValue(1.23f).getValue())
        assertEquals(1.23, KuzuValue(1.23).getValue())
        assertEquals("hello", KuzuValue("hello").getValue())
    }

    @Test
    fun testIncorrectTypeCast() {
        val value = KuzuValue("hello")
        try {
            value.getValue<Int>()
        } catch (e: ClassCastException) {
            // Expected
        }
    }

    @Test
    fun testListValue() {
        val list= arrayOf(KuzuValue(1), KuzuValue(2))
        val kuzuList = KuzuList(list)
        assertEquals(2, kuzuList.getListSize())
        assertEquals(1, kuzuList.getListElement(0).getValue())
        assertEquals(2, kuzuList.getListElement(1).getValue())
    }

    @Test
    fun testMapValue() {
        val mapKeys : Array<KuzuValue> = arrayOf(KuzuValue("a"), KuzuValue("b"))
        val mapValues : Array<KuzuValue> = arrayOf(KuzuValue(1), KuzuValue(2))
        val kuzuMap = KuzuMap(mapKeys, mapValues)
        assertEquals(2, kuzuMap.getNumFields())
        assertEquals(1, kuzuMap.getValue(0).getValue())
        assertEquals(2, kuzuMap.getValue(1).getValue())
    }

    @Test
    fun testStructValue() {
        val fieldNames = arrayOf("name", "age", "gender")
        val fieldValues = arrayOf(KuzuValue("John"), KuzuValue(12), KuzuValue("Male") )
        val kuzuStruct = KuzuStruct(fieldNames, fieldValues)
        assertEquals(3, kuzuStruct.getNumFields())
        assertEquals("John", kuzuStruct.getValueByIndex(0).getValue())
        assertEquals(12, kuzuStruct.getValueByIndex(1).getValue())
        assertEquals("Male", kuzuStruct.getValueByIndex(2).getValue())
    }
}