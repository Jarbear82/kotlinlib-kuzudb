import com.tau.kuzudb.KuzuTypeException
import com.tau.kuzudb.value.KuzuValue

fun convertToNative(kuzuValue: KuzuValue): com.kuzudb.Value {
    return when (kuzuValue) {
        is KuzuValue.BOOL -> com.kuzudb.Value(kuzuValue.nativeValue)
        is KuzuValue.INT8 -> com.kuzudb.Value(kuzuValue.nativeValue)
        is KuzuValue.INT16 -> com.kuzudb.Value(kuzuValue.nativeValue)
        is KuzuValue.INT32 -> com.kuzudb.Value(kuzuValue.nativeValue)
        is KuzuValue.INT64 -> com.kuzudb.Value(kuzuValue.nativeValue)
        is KuzuValue.FLOAT -> com.kuzudb.Value(kuzuValue.nativeValue)
        is KuzuValue.DOUBLE -> com.kuzudb.Value(kuzuValue.nativeValue)
        is KuzuValue.STRING -> com.kuzudb.Value(kuzuValue.nativeValue)
        is KuzuValue.BLOB -> com.kuzudb.Value(kuzuValue.nativeValue)
        is KuzuValue.LIST -> com.kuzudb.Value(kuzuValue.nativeValue.map { convertToNative(it) })
        is KuzuValue.MAP -> com.kuzudb.Value(kuzuValue.nativeValue.mapValues { convertToNative(it.value) })
        is KuzuValue.NULL -> com.kuzudb.Value(null)
        else -> throw KuzuTypeException("Unsupported KuzuValue type: ${kuzuValue::class.simpleName}")
    }
}

fun convertFromNative(nativeValue: com.kuzudb.Value): KuzuValue {
    if (nativeValue.isNull) return KuzuValue.NULL

    return when (val value = nativeValue) {
        is Boolean -> KuzuValue.BOOL(value)
        is Byte -> KuzuValue.INT8(value)
        is Short -> KuzuValue.INT16(value)
        is Int -> KuzuValue.INT32(value)
        is Long -> KuzuValue.INT64(value)
        is Float -> KuzuValue.FLOAT(value)
        is Double -> KuzuValue.DOUBLE(value)
        is String -> KuzuValue.STRING(value)
        is ByteArray -> KuzuValue.BLOB(value)
        is List<*> -> KuzuValue.LIST(value.map { convertFromNative(it as com.kuzudb.Value) })
        is Map<*, *> -> KuzuValue.MAP(
            (value as Map<String, com.kuzudb.Value>).mapValues { convertFromNative(it.value) }
        )
        // Add other conversions as needed...
        else -> throw KuzuTypeException("Unsupported native type: ${value.javaClass.name}")
    }
}