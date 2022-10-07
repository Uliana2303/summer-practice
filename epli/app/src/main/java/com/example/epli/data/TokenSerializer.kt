package com.example.epli.data

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream



private const val DATA_STORE_FILE_NAME = "auth_token.pb"

val Context.tokenStore: DataStore<Token> by dataStore(fileName = DATA_STORE_FILE_NAME,
    serializer = TokenSerializer,
    corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { Token.getDefaultInstance() })
)

object TokenSerializer: androidx.datastore.core.Serializer<Token> {
    override val defaultValue: Token
        get() = Token.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Token {
        try {
            return Token.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read token proto", e)
        }
    }

    override suspend fun writeTo(t: Token, output: OutputStream) {
        t.writeTo(output)
    }

}