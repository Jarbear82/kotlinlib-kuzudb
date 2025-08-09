package com.tau.kuzudb

import com.kuzudb.DataTypeID

actual enum class KuzuDataTypeID(nativeType: DataTypeID) {
    ANY(DataTypeID.ANY),
    ARRAY(DataTypeID.ARRAY),
    BLOB(DataTypeID.BLOB),
    BOOL(DataTypeID.BOOL),
    DATE(DataTypeID.DATE),
    DECIMAL(DataTypeID.DECIMAL),
    DOUBLE(DataTypeID.DOUBLE),
    FLOAT(DataTypeID.FLOAT),
    INT128(DataTypeID.INT128),
    INT16(DataTypeID.INT16),
    INT32(DataTypeID.INT32),
    INT8(DataTypeID.INT8),
    INTERNAL_ID(DataTypeID.INTERNAL_ID),
    INTERVAL(DataTypeID.INTERVAL),
    LIST(DataTypeID.LIST),
    MAP(DataTypeID.MAP),
    NODE(DataTypeID.NODE),
    RECURSIVE_REL(DataTypeID.RECURSIVE_REL),
    REL(DataTypeID.REL),
    SERIAL(DataTypeID.SERIAL),
    STRING(DataTypeID.STRING),
    STRUCT(DataTypeID.STRUCT),
    TIMESTAMP(DataTypeID.TIMESTAMP),
    TIMESTAMP_MS(DataTypeID.TIMESTAMP_MS),
    TIMESTAMP_NS(DataTypeID.TIMESTAMP_NS),
    TIMESTAMP_SEC(DataTypeID.TIMESTAMP_SEC),
    TIMESTAMP_TZ(DataTypeID.TIMESTAMP_TZ),
    UINT16(DataTypeID.UINT16),
    UINT32(DataTypeID.UINT32),
    UINT64(DataTypeID.UINT64),
    UINT8(DataTypeID.UINT8),
    UNION(DataTypeID.UNION),
    UUID(DataTypeID.UUID)
}