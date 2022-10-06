package ru.epli.database.users_has_series

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import ru.epli.database.series.SeriesModel
import ru.epli.database.users.UserModel

object UsersHasSeriesModel : Table("users_has_series") {
    private val user_id = reference("user_id", UserModel.id).uniqueIndex("users_has_series_un")
    private val series_id = reference("series_id", SeriesModel.id).uniqueIndex("users_has_series_un")
    private val series_viewed = UsersHasSeriesModel.integer("series_viewed")
    private val rating = UsersHasSeriesModel.integer("rating")
    private val notes = UsersHasSeriesModel.varchar("notes", 200)

    fun  insert(usersHasSeriesDTO: UsersHasSeriesDTO) {
        transaction {
            UsersHasSeriesModel.insert{
                it[user_id] = usersHasSeriesDTO.user_id
                it[series_id] = usersHasSeriesDTO.series_id
                it[series_viewed] = usersHasSeriesDTO.series_viewed
                it[rating] = usersHasSeriesDTO.rating
                it[notes] = usersHasSeriesDTO.notes
            }
        }
    }

    fun updateViewed(userId: Int, seriesId: Int, viewed: Int) {
        transaction {
            UsersHasSeriesModel.update(where =  {UsersHasSeriesModel.user_id.eq(userId) and UsersHasSeriesModel.series_id.eq(seriesId)}) {
                it[series_viewed] = viewed
            }
        }
    }
    fun updateRating(userId: Int, seriesId: Int, rating: Int) {
        transaction {
            UsersHasSeriesModel.update(where =  {UsersHasSeriesModel.user_id.eq(userId) and UsersHasSeriesModel.series_id.eq(seriesId)}) {
                it[UsersHasSeriesModel.rating] = rating
            }
        }
    }

    fun updateNotes(userId: Int, seriesId: Int, notes: String) {
        transaction {
            UsersHasSeriesModel.update(where =  {UsersHasSeriesModel.user_id.eq(userId) and UsersHasSeriesModel.series_id.eq(seriesId)}) {
                it[UsersHasSeriesModel.notes] = notes
            }
        }
    }
}