buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Deps.android_gradle_plugin)
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}")

        classpath(kotlin("gradle-plugin", Versions.kotlin))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://kotlin.bintray.com/kotlinx")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}