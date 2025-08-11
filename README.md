# KuzuDB-Kotlin

[](https://www.google.com/search?q=https://github.com/jarbear82/kotlinlib-kuzudb/actions/workflows/gradle.yml)
[](https://www.google.com/search?q=https://search.maven.org/artifact/io.github.jarbear82/kuzudb-kt)
[](https://opensource.org/licenses/Apache-2.0)

## What is it?

This repository contains a Kotlin Multiplatform library for interacting with the [KuzuDB](https://kuzudb.com/) graph database. The library provides a unified, idiomatic Kotlin API for Android and JVM platforms, with plans to support more platforms in the future.

## Features

  * **Kotlin Multiplatform:** Write your database code once and run it on both JVM and Android.
  * **Idiomatic Kotlin:** The library is designed to be used with modern Kotlin features like coroutines for asynchronous operations.
  * **Resource Management:** Implements `AutoCloseable` on KuzuDB resources for safe and easy resource handling.
  * **Comprehensive API:** Provides coverage for most of the KuzuDB Java driver's features.

## Installation

To add this library to your project, add the following dependency to your `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("io.github.jarbear82:kuzudb-kt:0.0.1")
}
```

## Usage

Here's a quick example of how to use the library:

```kotlin
import com.tau.kuzudb.KuzuDatabase
import com.tau.kuzudb.KuzuConnection

fun main() = runBlocking {
    // Use ":memory:" for an in-memory database
    KuzuDatabase(":memory:").use { db ->
        KuzuConnection(db).use { conn ->
            // Create a node table
            conn.query("CREATE NODE TABLE User(name STRING, age INT64, PRIMARY KEY (name))")

            // Create nodes
            conn.query("CREATE (a:User {name: 'Alice', age: 25})")
            conn.query("CREATE (b:User {name: 'Bob', age: 30})")

            // Query the database
            val result = conn.query("MATCH (u:User) RETURN u.name, u.age")

            // Iterate through the results
            for (tuple in result) {
                println("Name: ${tuple.getValue(0)}, Age: ${tuple.getValue(1)}")
            }
        }
    }
}
```

## Building from Source

To build the library from source, you'll need JDK 17 or higher.

1.  Clone the repository:
    ```bash
    git clone https://github.com/jarbear82/kotlinlib-kuzudb.git
    cd kotlinlib-kuzudb
    ```
2.  Build the project using the Gradle wrapper:
    ```bash
    ./gradlew build
    ```
3.  To run the tests:
    ```bash
    ./gradlew check
    ```

## Contributing

Contributions are welcome\! If you'd like to contribute, please fork the repository and create a pull request.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](https://www.google.com/search?q=https://github.com/jarbear82/kotlinlib-kuzudb/blob/main/LICENSE) file for details.
