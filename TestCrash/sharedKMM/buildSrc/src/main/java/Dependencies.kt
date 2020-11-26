object Versions {
    const val min_sdk = 21
    const val target_sdk = 30
    const val compile_sdk = 30

    const val kotlin = "1.4.10"
    const val android_gradle_plugin = "4.1.0"
    const val ktor = "1.4.2"
}

object Deps {
    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"

    object Ktor {
        const val commonCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val commonJson = "io.ktor:ktor-client-json:${Versions.ktor}"
        const val jvmCore =     "io.ktor:ktor-client-core-jvm:${Versions.ktor}"
        const val jvmJson =     "io.ktor:ktor-client-json-jvm:${Versions.ktor}"
        const val ios =         "io.ktor:ktor-client-ios:${Versions.ktor}"
        const val commonSerialization ="io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val androidSerialization ="io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
        const val androidLogging = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
        const val commonLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        const val androidCore = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
    }
}
