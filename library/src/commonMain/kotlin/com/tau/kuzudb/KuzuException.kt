package com.tau.kuzudb

/**
 * Custom exception for KuzuDB related errors.
 *
 * @param message The detail message for this exception.
 * @param cause The cause of this exception.
 */
open class KuzuException(message: String, cause: Throwable? = null) : Exception(message, cause)

class KuzuQueryException(message: String) : KuzuException(message)
class KuzuConnectionException(message: String) : KuzuException(message)
class KuzuTypeException(message: String) : KuzuException(message)