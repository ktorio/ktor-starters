plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "2.0.21"

    alias(libs.plugins.ktor)
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    // ktor starters
    implementation(projects.subprojects.starterKtorWeb)
    implementation(projects.subprojects.starterKtorExposed)

    // database
    implementation(libs.h2)
    // implementation(libs.postgres)
}
