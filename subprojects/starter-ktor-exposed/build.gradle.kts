plugins {
    id("ktor-sample.kotlin-jvm")
}

dependencies {
    compileOnly(libs.ktor.server.core)

    api(libs.exposed.core)
    api(libs.exposed.jdbc)
    api(libs.exposed.dao)

    compileOnly(libs.h2)
    compileOnly(libs.postgres)
}
