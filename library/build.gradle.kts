import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "io.github.jarbear82"
version = "1.0.0"

kotlin {
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    sourceSets {
        val commonMain by getting {

            dependencies {
                implementation(libs.kuzu)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }

        }
        val jvmMain by getting {
            dependencies {
                // Add jvm-specific dependencies here
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.junit)
                implementation(libs.mockito.core)
                implementation(libs.mockito.kotlin)
            }
        }
        val androidMain by getting {
            val androidMain by getting {
                kotlin.srcDirs += "src/jvmMain/kotlin"
            }

        }

    }
}

android {
    namespace = "com.tau.kuzudb"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    sourceSets {
        getByName("main") {
            java.srcDirs("src/javaMain/kotlin")

        }
    }
}


mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), "kuzudb-kt", version.toString())

    pom {
        name = "KuzuDB-Kotlin"
        description = "A Kotlin Multiplatform library for KuzuDB."
        inceptionYear = "2024"
        url = "https://github.com/jarbear82/kotlinlib-kuzudb/"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "repo"

            }
        }
        developers {
            developer {
                id = "jarbear82"
                name = "Jared медведь"
                url = "https://github.com/jarbear82/"

            }
        }
        scm {
            url = "https://github.com/jarbear82/kotlinlib-kuzudb/"
            connection = "scm:git:git://github.com/jarbear82/kotlinlib-kuzudb.git"
            developerConnection = "scm:git:ssh://git@github.com/jarbear82/kotlinlib-kuzudb.git"
        }
    }
}