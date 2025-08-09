package com.tau.kuzudb

import com.tau.kuzudb.value.KuzuValue

/**
 * Represents a single row in a KuzuQueryResult.
 *
 * @param values The list of values in the tuple.
 */
data class KuzuTuple(val values: List<KuzuValue>)