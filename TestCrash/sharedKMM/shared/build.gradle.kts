import org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
}

group = "com.experiment"
version = "0.0.1"

android {
    compileSdkVersion(Versions.compile_sdk)
    defaultConfig {
        minSdkVersion(Versions.min_sdk)
        targetSdkVersion(Versions.target_sdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        proguardFiles("proguard-rules.pro")
        consumerProguardFiles("consumer-rules.pro")
        javaCompileOptions.annotationProcessorOptions.includeCompileClasspath = true
    }


    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

val frameworkName = "shared"

kotlin {
    val iosArm64 = iosArm64("iosArm64")
    val iosX64 = iosX64("iosX64")
    ios("ios")
    android()

    configure(listOf(iosArm64, iosX64)) {
        binaries.framework {
            baseName = frameworkName
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.RequiresOptIn")
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
    }

    sourceSets["commonMain"].dependencies {
        implementation(kotlin("stdlib-common", Versions.kotlin))
        implementation(Deps.Ktor.commonCore)
        implementation(Deps.Ktor.commonJson)
        implementation(Deps.Ktor.commonLogging)
        implementation(Deps.Ktor.commonSerialization)
    }

    sourceSets["commonTest"].dependencies {
    }

    sourceSets["androidMain"].dependencies {
        api(kotlin("stdlib", Versions.kotlin))
        api(Deps.Ktor.jvmCore)
        api(Deps.Ktor.jvmJson)
        api(Deps.Ktor.androidLogging)
        api(Deps.Ktor.androidSerialization)
        api(Deps.Ktor.androidCore)
    }

    sourceSets["androidTest"].dependencies {
    }

    sourceSets["iosMain"].dependencies {
        implementation(Deps.Ktor.ios)
    }

    sourceSets["iosTest"]
    sourceSets["iosArm64Main"].dependsOn(sourceSets["iosMain"])
    sourceSets["iosArm64Test"].dependsOn(sourceSets["iosTest"])
    sourceSets["iosX64Main"].dependsOn(sourceSets["iosMain"])
    sourceSets["iosX64Test"].dependsOn(sourceSets["iosTest"])

    tasks.create("debugFatFramework", FatFrameworkTask::class) {
        baseName = frameworkName
        destinationDir = buildDir.resolve("universal/debug")

        from(
                iosArm64.binaries.getFramework("DEBUG"),
                iosX64.binaries.getFramework("DEBUG")
        )
    }

    tasks.create("releaseFatFramework", FatFrameworkTask::class) {
        baseName = frameworkName
        destinationDir = buildDir.resolve("universal/release")

        from(
                iosArm64.binaries.getFramework("RELEASE"),
                iosX64.binaries.getFramework("RELEASE")
        )
    }
}

tasks.build {
    finalizedBy("debugFatFramework", "releaseFatFramework")
}