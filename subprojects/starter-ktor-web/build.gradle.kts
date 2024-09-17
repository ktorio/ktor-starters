plugins {
    id("ktor-sample.kotlin-jvm")


}

dependencies {
    api(libs.ktor.server.core)
    api(libs.ktor.serialization.kotlinx.json)
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    api(libs.ktor.server.content.negotiation)
    api(libs.ktor.server.default.headers)
    api(libs.ktor.server.compression)
    api(libs.ktor.server.netty)
    api(libs.logback.classic)
    api(libs.ktor.server.config.yaml)
}
