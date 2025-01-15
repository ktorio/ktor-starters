package io.ktor.starter.web

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        module = {
            defaultConfiguration()
        }
    ).start(wait = true)
}

fun Application.defaultConfiguration() {
    install(ContentNegotiation) {
        json()
    }
    install(Compression)
}
