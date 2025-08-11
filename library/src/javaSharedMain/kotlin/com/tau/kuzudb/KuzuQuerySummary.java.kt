package com.tau.kuzudb

actual class KuzuQuerySummary {
    internal val nativeSummary: com.kuzudb.QuerySummary

    internal constructor(nativeSummary: com.kuzudb.QuerySummary) {
        this.nativeSummary = nativeSummary
    }
    /**
     * Construct a new query summary.
     */
    actual constructor(cmpTime: Double, exeTime: Double) {
        this.nativeSummary = com.kuzudb.QuerySummary(cmpTime, exeTime)
    }

    /**
     * Get the compiling time of the query.
     */
    actual fun getCompilingTime(): Double = nativeSummary.compilingTime

    /**
     * Get the execution time of the query.
     */
    actual fun getExecutionTime() {
        nativeSummary.executionTime
    }
}