package com.example.ktor

import io.ktor.starter.exposed.*
import io.ktor.starter.web.*

fun main() {
    startServer {
        installExposed(InMemoryH2) {
            createSchema(UserEntity)
        }

//        installExposed(
//            JdbcConfiguration(
//                url = "jdbc:postgresql://localhost:5432/ktor-sample",
//                username = "ktor-sample",
//                password = "ktor-sample"
//            )
//        ) {
//            createSchema(UserEntity)
//        }

        configureRoutes()
    }
}
