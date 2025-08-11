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
}