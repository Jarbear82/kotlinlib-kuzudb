package com.tau.kuzudb

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class KuzuDataTypeTest {

    @Test
    fun testDataTypeCreation() {
        val type = KuzuDataType(KuzuDataTypeID.STRING)
        assertNotNull(type, "DataType object should not be null")
        assertEquals(KuzuDataTypeID.STRING, type.getID(), "Data type ID should be STRING")
    }
}