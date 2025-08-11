package com.tau.kuzudb

expect object KuzuValueRecursiveRelUtil {
    /**
     * Get the node list from the given recursive_rel value.
     */
    fun getNodeList(value: KuzuValue) : KuzuValue

    /**
     * Get the rel list from the given recursive_rel value.
     */
    fun getRelList(value: KuzuValue) : KuzuValue
}