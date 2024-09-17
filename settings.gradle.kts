@file:Suppress("UnstableApiUsage")

rootProject.name = "ktor-htmx-hackathon"

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
    ":subprojects:extension-ktor",
    ":subprojects:extension-kotlinx-html",
    ":subprojects:sample-ktor-kotlinx-html",
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
