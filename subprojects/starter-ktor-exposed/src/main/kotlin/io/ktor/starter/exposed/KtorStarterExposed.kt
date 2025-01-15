package io.ktor.starter.exposed

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.installExposed(
    databaseConfiguration: DatabaseConfiguration,
    exposedConfiguration: ExposedConfiguration.() -> Unit
) {
    val database = databaseConfiguration.create()
    DatabaseHolder.database = database

    val localExposedConfiguration = object : ExposedConfiguration {
        override fun <T : Table> createSchema(vararg tables: T) {
            transaction(database) {
                SchemaUtils.create(*tables)
            }
        }
    }

    with(localExposedConfiguration) {
        exposedConfiguration()
    }
}

interface ExposedConfiguration {
    fun <T : Table> createSchema(vararg tables: T)
}

internal object DatabaseHolder {
    lateinit var database: Database

    fun get(): Database = if (this::database.isInitialized) database else error("Call installExposed() first")
}
