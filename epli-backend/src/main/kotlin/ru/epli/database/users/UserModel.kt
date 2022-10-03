package ru.epli.database.users

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction


object UserModel: IdTable<Int>("users") {
    override val id = UserModel.integer("id").autoIncrement().entityId()
    private val password = UserModel.varchar("password", 25)
    private val email = UserModel.varchar("email", 50)
    private val login = UserModel.varchar("login", 25)

    fun insert(userDTO: UserDTO) {
        transaction {
            UserModel.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[email] = userDTO.email
            }
        }
    }

    fun fetchUser(email: String): UserDTO? {
        return try {
            transaction {
                val userModel = UserModel.select { UserModel.email.eq(email)}.single()
                UserDTO(
                    login = userModel[UserModel.login],
                    password = userModel[password],
                    email = userModel[UserModel.email],
                    id = userModel[UserModel.id].value
                )
            }

        } catch (e: Exception) {
             null
        }

    }
}