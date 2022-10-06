package ru.epli.database.genres

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.IOException

object GenresModel: IdTable<Int>("genres"){
    override val id = GenresModel.integer("id").autoIncrement().entityId()
    private val genre_name = GenresModel.varchar("genre_name", 30)

    fun fetchGenres(): List<GenresDTO> {
        return try {
            transaction {
                GenresModel.selectAll().toList().map {
                    GenresDTO(
                        id = it[GenresModel.id].value,
                        genre_name = it[genre_name]
                    )
                }
            }
        } catch (e : IOException) {
            emptyList()
        }
    }
}