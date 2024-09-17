package io.ktor.starter.web

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {
    startServer {
        defaultConfiguration()
    }
}

inline fun startServer(port: Int = 8080, crossinline configuration: Application.() -> Unit = {}) {
    embeddedServer(
        factory = Netty,
        port = port,
        module = {
            this.defaultConfiguration()
            this.configuration()
        }
    ).start(wait = true)
}

fun Application.defaultConfiguration() {
    install(ContentNegotiation) {
        json()
    }
    install(Compression)
}
