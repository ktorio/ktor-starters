package io.ktor.starter.exposed

import org.jetbrains.exposed.sql.Database

interface DatabaseConfiguration {
    fun create(): Database
}

data class JdbcConfiguration(
    val url: String,
    val username: String,
    val password: String
) : DatabaseConfiguration {

    override fun create(): Database {
        val jdbcDriver = when {
            url.contains(":h2:") -> "org.h2.Driver"
            url.contains(":postgresql") -> "org.postgresql.Driver"
            else -> error("Cannot determine which driver to use for $url")
        }

        if (!isClassPresent(jdbcDriver)) {
            throw IllegalStateException("Driver $jdbcDriver is not present on the classpath. Add it as a dependency")
        }

        return Database.connect(
            url = url,
            user = username,
            driver = jdbcDriver,
            password = password,
        )
    }
}

object InMemoryH2 : DatabaseConfiguration {
    override fun create(): Database {
        if (!isClassPresent("org.h2.Driver")) {
            throw IllegalStateException("Please add H2 as a dependency")
        }

        val database = Database.connect(
            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            user = "root",
            driver = "org.h2.Driver",
            password = "",
        )
        return database
    }
}

private fun isClassPresent(clazz: String): Boolean {
    return try {
        Class.forName(clazz)
        true
    } catch (e: ClassNotFoundException) {
        false
    }
}

