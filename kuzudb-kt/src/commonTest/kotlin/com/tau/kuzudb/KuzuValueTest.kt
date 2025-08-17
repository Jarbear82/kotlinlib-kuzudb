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
        val list = KuzuValue(listOf(1, 2))
        val kuzuList = KuzuList(list)
        assertEquals(2, kuzuList.getListSize())
        assertEquals(1L, kuzuList.getListElement(0).getValue())
    }

    @Test
    fun testMapValue() {
        val map = KuzuValue(mapOf("a" to 1, "b" to 2))
        val kuzuMap = KuzuMap(map)
        assertEquals(2, kuzuMap.getNumFields())
        assertEquals(1L, kuzuMap.getValue(0).getValue())
    }

    @Test
    fun testStructValue() {
        val struct = KuzuValue(mapOf("a" to 1, "b" to 2))
        val kuzuStruct = KuzuStruct(struct)
        assertEquals(2, kuzuStruct.getNumFields())
        assertEquals(1L, kuzuStruct.getValueByIndex(0).getValue())
    }
}