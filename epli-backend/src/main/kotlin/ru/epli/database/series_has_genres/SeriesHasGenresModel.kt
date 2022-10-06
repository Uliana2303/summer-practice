package ru.epli.database.series_has_genres

import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import ru.epli.database.genres.GenresModel
import ru.epli.database.series.SeriesModel

object SeriesHasGenresModel : Table("series_has_genres") {
    private val genre_id = reference("genre_id", GenresModel.id).uniqueIndex("siries_has_genre_un")
    private val series_id = reference("series_id", SeriesModel.id).uniqueIndex("siries_has_genre_un")

    fun querySeriesByGenreId(genresIdList: List<Int>): Query {
        print(transaction {
            SeriesHasGenresModel
                .slice(series_id)
                .select(
                    SeriesHasGenresModel.genre_id.inList(genresIdList)
                )
        })

        return transaction {
            SeriesHasGenresModel
                .slice(series_id)
                .select(
                    SeriesHasGenresModel.genre_id.inList(genresIdList)
                )
        }
    }
}