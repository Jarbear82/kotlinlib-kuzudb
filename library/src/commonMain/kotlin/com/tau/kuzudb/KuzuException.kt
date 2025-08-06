package com.tau.kuzudb

/**
 * Custom exception for KuzuDB related errors.
 *
 * @param message The detail message for this exception.
 * @param cause The cause of this exception.
 */
class KuzuException(message: String, cause: Throwable? = null) : Exception(message, cause)
