package com.example.ktor

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.starter.exposed.*

fun Application.configureRoutes() {

    val userRepository = CrudRepository(UserEntity)

    routing {
        get("/users") {
            val users = userRepository.findAll()
            call.respond(users)
        }

        post("/user") {
            val user = call.receive<User>()
            userRepository.insert(user)

            call.respond(HttpStatusCode.OK)
        }

        get("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: error("No id")
            val user = userRepository.findById(id) ?: error("User not found")
            call.respond(user)
        }

        get("/info") {
            call.respondText("Database: ${database.vendor}")
        }
    }
}
