package io.ktor.starter.exposed

import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

val Application.database
    get() = DatabaseHolder.get()

val RoutingContext.database
    get() = DatabaseHolder.get()

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }
