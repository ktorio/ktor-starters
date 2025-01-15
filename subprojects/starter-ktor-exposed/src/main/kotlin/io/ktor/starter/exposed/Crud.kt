package io.ktor.starter.exposed

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.update

class CrudRepository<E : CrudTable<E, M>, M>(
    private val table: CrudTable<E, M>
) {

    suspend fun findAll(): List<M> {
        return dbQuery {
            table.selectAll()
                .map { table.toModel(it) }
        }
    }

    suspend fun findById(id: Int): M? {
        return dbQuery {
            table.selectAll()
                .where { table.id eq id }
                .map { table.toModel(it) }
                .singleOrNull()
        }
    }

    suspend fun insert(model: M): Int {
        return dbQuery {
            table.insert {
                table.prepareUpdate(model, it)
            }[table.id]
        }
    }

    suspend fun update(model: M) {
        dbQuery {
            table.update {
                table.prepareUpdate(model, it)
            }
        }
    }
}

abstract class CrudTable<E, M>(name: String = "") : Table(name) {
    val id = integer("id").autoIncrement()

    abstract fun prepareUpdate(model: M, updateBuilder: UpdateBuilder<Int>)
    abstract fun toModel(row: ResultRow): M

    override val primaryKey = PrimaryKey(id)
}
