package com.example.epli.network.models

@kotlinx.serialization.Serializable
data class AnswerRespondRemote(
    val id: Int,
    val questionId: Int?,
    val content: String,
    val correct: Boolean?
)