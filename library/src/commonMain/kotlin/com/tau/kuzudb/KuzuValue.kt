package com.tau.kuzudb

expect class KuzuValue() : AutoCloseable {
    override fun close()


}