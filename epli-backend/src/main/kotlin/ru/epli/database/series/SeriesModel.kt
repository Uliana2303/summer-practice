package ru.epli.database.series

import io.ktor.utils.io.errors.*
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inSubQuery
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.transactions.transaction
import ru.epli.database.series_has_genres.SeriesHasGenresModel


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

    fun fetchSeriesByQueryAndGenresIds(query: String, genresIdList: List<Int>): List<SeriesDTO> {
        return try {
            if (genresIdList.isEmpty()) {
                transaction {
                    SeriesModel.select(SeriesModel.name.lowerCase().like("%$query%".lowercase())).toList()
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
            } else {

                print(transaction {
                    SeriesModel.select(SeriesModel.name.lowerCase().like("%$query%".lowercase()) and
                            SeriesModel.id.inSubQuery(SeriesHasGenresModel.querySeriesByGenreId(genresIdList)))
                        .toList().map {
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
                })

                transaction {
                    SeriesModel.select(SeriesModel.name.lowerCase().like("%$query%".lowercase()) and
                                SeriesModel.id.inSubQuery(SeriesHasGenresModel.querySeriesByGenreId(genresIdList)))
                        .toList().map {
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
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun fetchAllSeries(): List<SeriesDTO> {
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

    fun getSeriesById(id: Int) : SeriesDTO? {
        return try {
            transaction {
                SeriesModel.select(SeriesModel.id.eq(id)).toList().map{
                    SeriesDTO(
                        series_id = it[SeriesModel.id].value,
                        name = it[name],
                        series_count = it[series_count],
                        description = it[description],
                        year = it[year],
                        seria_time = it[seria_time],
                        poster = it[poster],
                    )
                }.single()
            }
        } catch (e : IOException) {
            null
        }
    }
}