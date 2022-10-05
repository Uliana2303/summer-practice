package com.example.epli.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class QuizType{
    @SerialName("quiz")
    Quiz,
    @SerialName("test")
    Test
}

data class QuizesRespondRemote(
    val id: Int,
    val creatorEmail: String,
    val group: String,
    val title: String,
    val description: String,
    val quizType: QuizType
)