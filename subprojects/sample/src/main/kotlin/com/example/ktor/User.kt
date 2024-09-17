package com.example.ktor

import io.ktor.starter.exposed.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder

@Serializable
data class User(
    val id: Int? = null,
    val name: String,
    val age: Int
)

object UserEntity : CrudTable<UserEntity, User>() {
    val name = varchar("name", length = 50)
    val age = integer("age")

    override fun prepareUpdate(model: User, updateBuilder: UpdateBuilder<Int>) {
        updateBuilder[name] = model.name
        updateBuilder[age] = model.age
    }

    override fun toModel(row: ResultRow): User {
        return User(
            id = row[id],
            name = row[name],
            age = row[age]
        )
    }
}
