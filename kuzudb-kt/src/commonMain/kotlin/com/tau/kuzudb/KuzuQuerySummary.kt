package com.tau.kuzudb

expect class KuzuQuerySummary {
    /**
     * Construct a new query summary.
     */
    constructor(cmpTime: Double, exeTime: Double)

    /**
     * Get the compiling time of the query.
     */
    fun getCompilingTime() : Double

    /**
     * Get the execution time of the query.
     */
    fun getExecutionTime(): Double

}