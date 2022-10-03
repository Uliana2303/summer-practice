package ru.epli.database.series

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


object SeriesModel: IdTable<Int>("series") {
    override val id = SeriesModel.integer("series_id").autoIncrement().entityId()
    private val name = SeriesModel.varchar("name", 75)
    private val series_count = SeriesModel.integer("series_count")
    private val description = SeriesModel.varchar("description", 1000)
    private val year = SeriesModel.integer("year")
    private val seria_time = SeriesModel.integer("seria_time")
    private val poster = SeriesModel.varchar("poster", 500)

    fun insert(seriesDTO: SeriesDTO) {
        transaction {
            SeriesModel.insert {
                it[id] = seriesDTO.series_id
                it[name] = seriesDTO.name
                it[series_count] = seriesDTO.series_count
                it[description] = seriesDTO.description
                it[year] = seriesDTO.year
                it[seria_time] = seriesDTO.seria_time
                it[poster] = seriesDTO.poster
            }
        }
    }

    fun fetchSeries(): List<SeriesDTO> {
        return try {
            transaction {
                SeriesModel.selectAll().toList()
                    .map {
                        SeriesDTO(
                            series_id = it[SeriesModel.id].value,
                            name = it[name],
                            series_count = it[series_count],
                            description = it[description],
                            year = it[year],
                            seria_time = it[seria_time],
                            poster = it[poster],
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

}