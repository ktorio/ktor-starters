@file:Suppress("UnstableApiUsage")

rootProject.name = "ktor-starter-demo"

pluginManagement {
    includeBuild("build-logic")

    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.8.0")
}

include(
    ":subprojects:starter-ktor-web",
    ":subprojects:starter-ktor-exposed",
    ":subprojects:sample",
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
