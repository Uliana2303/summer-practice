package ru.epli.database.tokens

import io.ktor.utils.io.errors.*
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


object TokenModel: IdTable<Int>("tokens") {
    override val id = TokenModel.integer("id").autoIncrement().entityId()
    private val email = TokenModel.varchar("email", 50)
    private val token = TokenModel.varchar("token", 75)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            TokenModel.insert {
                it[token] = tokenDTO.token
                it[email] = tokenDTO.email
            }
        }
    }

    fun getEmailByToken(token: String) : String? {
        return try {
            transaction {
                TokenModel.select { TokenModel.token.eq(token) }.toList()
                    .map {
                        it[email]
                    }.single()
            }
        } catch (e : IOException) {
            return null
        }
    }


    fun fetchTokens(): List<TokenDTO> {
        return try {
            transaction {
                TokenModel.selectAll().toList()
                    .map {
                        TokenDTO(
                            id = it[TokenModel.id].value,
                            token = it[token],
                            email = it[email]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

}